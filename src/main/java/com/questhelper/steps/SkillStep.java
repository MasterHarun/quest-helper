package com.questhelper.steps;

import com.questhelper.QuestBank;
import com.questhelper.questhelpers.QuestHelper;
import com.questhelper.requirements.Requirement;
import com.questhelper.requirements.conditional.InitializableRequirement;
import com.questhelper.requirements.item.ItemRequirement;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import javax.inject.Inject;
import net.runelite.api.GameState;
import net.runelite.api.events.GameStateChanged;
import net.runelite.api.events.GameTick;
import net.runelite.client.eventbus.EventBus;
import net.runelite.client.eventbus.Subscribe;


public class SkillStep extends QuestStep implements OwnerStep
{
	@Inject
	protected EventBus eventBus;

	@Inject
	protected QuestBank questBank;


	protected boolean started = false;

	protected final LinkedHashMap<Requirement, QuestStep> steps;

	protected QuestStep currentStep;

	protected Requirement[] requirements;

	public SkillStep(QuestHelper questHelper, QuestStep step, Requirement... requirements)
	{
		super(questHelper);
		this.requirements = requirements;
		this.steps = new LinkedHashMap<>();

		this.steps.put(null, step);
	}

	public void addStep(Requirement requirement, QuestStep step)
	{
		addStep(requirement, step, false);
	}

	public void addStep(Requirement requirement, QuestStep step, boolean isLockable)
	{
		step.setLockable(isLockable);
		this.steps.put(requirement, step);

	}

	@Override
	public void startUp()
	{

		steps.keySet().stream()
			.filter(InitializableRequirement.class::isInstance)
			.forEach(req -> ((InitializableRequirement) req).initialize(client));
		updateSteps();
		started = true;
	}

	@Override
	public void shutDown()
	{
		started = false;
		shutDownStep();
		currentStep = null;
	}

	@Subscribe
	public void onGameTick(GameTick event)
	{
		if (started)
		{
			updateSteps();
		}
	}

	@Subscribe
	public void onGameStateChanged(final GameStateChanged event)
	{
		if (event.getGameState() == GameState.LOADING || event.getGameState() == GameState.HOPPING)
		{
			steps.keySet().stream()
				.filter(Objects::nonNull)
				.filter(InitializableRequirement.class::isInstance)
				.forEach(req -> ((InitializableRequirement) req).updateHandler());
		}
	}


	protected void updateSteps()
	{
		Requirement lastPossibleCondition = null;
		LinkedHashMap<Requirement, Boolean> reqs = new LinkedHashMap<>();
		Iterator<Map.Entry<Requirement, QuestStep>> stepItr = steps.entrySet().iterator();

		while(stepItr.hasNext())
		{
			Map.Entry<Requirement, QuestStep> entry = stepItr.next();

			if (entry.getKey() != null)
			{
				for (Requirement req : entry.getValue().getRequirements())
				{
					if (req instanceof ItemRequirement)
					{
						ItemRequirement itemReq;
						itemReq = ((ItemRequirement) req);
						itemReq = itemReq.alsoCheckBank(questBank);
						if (itemReq.check(client))
						{
							reqs.put(req, true);
						}
						else
						{
							reqs.put(req, false);
						}
					}

				}
			}

			Requirement conditions = entry.getKey();
			boolean stepIsLocked = steps.get(conditions).isLocked();
			if (conditions != null && conditions.check(client) && !stepIsLocked)
			{

				if (!reqs.containsValue(false))
				{
					startUpStep(steps.get(conditions));
					reqs.clear();
					return;
				}
				lastPossibleCondition = conditions;
			}
			else if (steps.get(conditions).isBlocker() && stepIsLocked)
			{

				startUpStep(steps.get(lastPossibleCondition));
				reqs.clear();
				return;
			}
			else if (conditions != null && !stepIsLocked)
			{

				lastPossibleCondition = conditions;
				reqs.clear();
			}
			reqs.clear();
		}

		if (!steps.get(null).isLocked())
		{
			startUpStep(steps.get(null));
		}
		else
		{
			startUpStep(steps.get(lastPossibleCondition));
		}
	}

	protected void startUpStep(QuestStep step)
	{
		if (currentStep == null)
		{
			eventBus.register(step);
			step.startUp();
			currentStep = step;
			return;
		}

		if (!step.equals(currentStep))
		{
			shutDownStep();
			eventBus.register(step);
			step.startUp();
			currentStep = step;
		}
	}

	protected void shutDownStep()
	{
		if (currentStep != null)
		{
			eventBus.unregister(currentStep);
			currentStep.shutDown();
			currentStep = null;
		}
	}

	@Override
	public QuestStep getActiveStep()
	{
		if (currentStep != null)
		{
			return currentStep.getActiveStep();
		}

		return this;
	}

	@Override
	public QuestStep getSidePanelStep()
	{
		if (text != null)
		{
			return this;
		}
		else if (currentStep != null)
		{
			return currentStep.getSidePanelStep();
		}

		return this;
	}

	public Collection<Requirement> getConditions()
	{
		return steps.keySet();
	}


	@Override
	public Collection<QuestStep> getSteps()
	{
		return steps.values();
	}
}
