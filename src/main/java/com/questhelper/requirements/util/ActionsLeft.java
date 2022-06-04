package com.questhelper.requirements.util;

import net.runelite.api.Experience;

public class ActionsLeft
{

	public static int get(double playerXp, int skillLevel, int lowerLevel, int nextLevel, double actionXp)
	{
		int nextLevelXp = Experience.getXpForLevel(nextLevel);
		int lowerLevelXp = Experience.getXpForLevel(lowerLevel);

		if (lowerLevel > skillLevel)
		{
			double xpLeft = nextLevelXp - lowerLevelXp;
			int actionsRemaining = (int) xpLeft / (int) actionXp;
			if (actionsRemaining <= 0)
			{
				return 0;
			}
			else
			{
				return (int) xpLeft / (int) actionXp;
			}
		}
		else
		{
			double xpLeft = nextLevelXp - playerXp;
			int actionsRemaining = (int) xpLeft / (int) actionXp;
			if (actionsRemaining <= 0)
			{
				return 0;
			}
			else
			{
				return (int) xpLeft / (int) actionXp;
			}
		}

	}
}
