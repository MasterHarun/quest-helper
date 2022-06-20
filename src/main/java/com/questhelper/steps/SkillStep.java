package com.questhelper.steps;

import com.questhelper.QuestBank;
import com.questhelper.questhelpers.QuestHelper;
import com.questhelper.requirements.Requirement;
import com.questhelper.requirements.conditional.InitializableRequirement;
import com.questhelper.requirements.item.ItemRequirement;
import com.questhelper.requirements.player.SkillRequirement;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
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

	protected final LinkedHashMap<Requirement, LinkedList<QuestStep>> steps;

	protected QuestStep currentStep;

	protected Requirement[] requirements;

	public SkillStep(QuestHelper questHelper, QuestStep step, Requirement... requirements)
	{
		super(questHelper);
		this.requirements = requirements;
		this.steps = new LinkedHashMap<>();
		LinkedList<QuestStep> quests = new LinkedList<>();

		quests.add(step);
		this.steps.put(null, quests);
	}

	public void addStep(Requirement requirement, QuestStep step)
	{
		addStep(requirement, step, false);
	}

	public void addStep(Requirement requirement, QuestStep step, boolean isLockable)
	{
		step.setLockable(isLockable);
		if (this.steps.containsKey(requirement))
		{
			this.steps.get(requirement).add(step);
		}
		else
		{
			LinkedList<QuestStep> quests = new LinkedList<>();
			quests.add(step);
			this.steps.put(requirement, quests);
		}
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
		LinkedList<QuestStep> questList = new LinkedList<>();
//		//check player skill level.
//		//check which steps have a range that has the player's skill level
//		//for each skillstep check...
//		//if player skill level is lower than the lower and higher levels continue
//		//if player skill level is lower than the higher level of the step and higher than the low level of the step
//		//push this step to an array of steps to check for items
//		// if player skill level is higher than the higher levels and lower levels stop and use the array. otherwise if not use highest possible step

		for (Requirement conditions : steps.keySet())
		{
			if (conditions != null)
			{
				int playerLevel = client.getRealSkillLevel((((SkillRequirement) conditions).getSkill()));
				int skillReq = ((SkillRequirement) conditions).getRequiredLevel();
				QuestStep quest = steps.get(conditions).getFirst();

				if (quest instanceof DetailedSkillStep)
				{
					int lowerLevel = ((DetailedSkillStep) quest).getLowerLevel();
					int highLevel = ((DetailedSkillStep) quest).getNextLevel();

					if (playerLevel < skillReq) { continue; }

					else if (playerLevel < highLevel && playerLevel >= lowerLevel)
					{
						if (steps.get(conditions).size() > 1)
						{
							LinkedList<QuestStep> skillSteps = steps.get(conditions);
							questList.addAll(skillSteps);
						}
						questList.add(quest);
					}

					else if (playerLevel > highLevel && playerLevel > lowerLevel)
					{
						QuestStep nextQuest = questItemCheck(questList);
						startUpStep(nextQuest);
					}
				}

				if (quest instanceof ObjectStep)
				{
					boolean stepIsLocked = steps.get(conditions).getFirst().isLocked();
					if (conditions.check(client) && !stepIsLocked)
					{
						startUpStep(steps.get(conditions).getFirst());
						return;
					}

					else if (steps.get(conditions).getFirst().isBlocker() && !stepIsLocked)
					{
						startUpStep(steps.get(lastPossibleCondition).getFirst());
						return;
					}

					else if (!stepIsLocked)
					{
						lastPossibleCondition = conditions;
					}
				}

			}

		}

	}

	protected QuestStep questItemCheck(LinkedList<QuestStep> questList)
	{
		if (questList == null)
		{
			return null;
		}
		LinkedHashMap<QuestStep, Boolean> questReqs = new LinkedHashMap<>();
		QuestStep quest = questList.getFirst();
		for (QuestStep quests : questList)
		{
			if (quests instanceof DetailedSkillStep)
			{
				List<Requirement> itemsList = ((DetailedSkillStep) quests).getRequirements();
				LinkedHashMap<Requirement, Boolean> itemReqs = new LinkedHashMap<>();

				for (Requirement item : itemsList)
				{
					if (item instanceof ItemRequirement)
					{
						if (item.check(client))
						{
							itemReqs.put(item, true);
						}
						else
						{
							itemReqs.put(item, false);
						}
					}
				}

				if (!itemReqs.containsValue(false))
				{
					return quests;
				}
				else if (itemReqs.containsValue(false) && itemReqs.containsValue(true))
				{
					questReqs.put(quests, false);
				}
				else if (!itemReqs.containsValue(true))
				{
					questReqs.put(quests, false);
				}
			}
		}
		if (!questReqs.containsValue(true))
		{
			quest = null;
		}
		return quest;
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
		return steps.values().stream().flatMap(LinkedList::stream).collect(Collectors.toList());
	}
}
