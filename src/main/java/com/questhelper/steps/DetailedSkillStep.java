package com.questhelper.steps;

import com.questhelper.questhelpers.QuestHelper;
import com.questhelper.requirements.Requirement;
import lombok.Getter;
import net.runelite.api.Experience;
import net.runelite.api.Skill;
import net.runelite.api.events.GameTick;

public class DetailedSkillStep extends DetailedQuestStep
{
	protected int nextLevel;
	protected int lowerLevel;
	protected double actionXp;
	protected int skillLevel;
	protected int playerXp;

	protected Skill skill;
	@Getter
	private String actionsRemaining;

	public DetailedSkillStep(QuestHelper questHelper, String text, Skill skill, int lowerLevel, int nextLevel, double actionXp,
							 Requirement... requirements)
	{
		super(questHelper, text, requirements);
		this.skill = skill;
		this.actionXp = actionXp;
		this.nextLevel = nextLevel;
		this.lowerLevel = lowerLevel;
	}

	public DetailedSkillStep(QuestHelper questHelper, String text, Requirement... requirements)
	{
		super(questHelper, text, requirements);
	}

	@Override
	public void startUp()
	{
		super.startUp();
		actionsLeft(lowerLevel, nextLevel, actionXp);
	}

	@Override
	public void shutDown()
	{
		super.shutDown();
	}

	@Override
	public void onGameTick(final GameTick event)
	{
		super.onGameTick(event);
		actionsLeft(lowerLevel, nextLevel, actionXp);
	}


	public void actionsLeft(int lowerLevel, int nextLevel, double actionXp)
	{
		if(lowerLevel == 0 || nextLevel == 0)
		{
			this.actionsRemaining = "0";
		}
		int nextLevelXp = Experience.getXpForLevel(nextLevel);
		int lowerLevelXp = Experience.getXpForLevel(lowerLevel);
		skillLevel = client.getRealSkillLevel(skill);
		playerXp = client.getSkillExperience(skill);

		if (lowerLevel > skillLevel)
		{
			double xpLeft = nextLevelXp - lowerLevelXp;
			int actionsRemaining = (int) xpLeft / (int) actionXp;
			String actsLeft = Integer.toString(actionsRemaining);
			if (actionsRemaining > 0)
			{
				this.actionsRemaining = actsLeft;
			}
		}
		else
		{
			double xpLeft = nextLevelXp - playerXp;
			int actionsRemaining = (int) xpLeft / (int) actionXp;
			String actsLeft = Integer.toString(actionsRemaining);
			if (actionsRemaining > 0)
			{
				this.actionsRemaining = actsLeft;

			}
		}

	}

}
