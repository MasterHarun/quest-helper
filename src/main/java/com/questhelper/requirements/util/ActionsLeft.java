package com.questhelper.requirements.util;

import net.runelite.api.Experience;

public class ActionsLeft
{

	public static int get(float playerXp, int skillLevel, int lowerLevel, int nextLevel, float actionXp)
	{
		int nextLevelXp = Experience.getXpForLevel(nextLevel);
		int lowerLevelXp = Experience.getXpForLevel(lowerLevel);

		if (lowerLevel > skillLevel)
		{
			float xpLeft = nextLevelXp - lowerLevelXp;
			float actionsRemaining = xpLeft / actionXp;
			if (actionsRemaining <= 0)
			{
				return 0;
			}
			else
			{
				return (int) actionsRemaining;
			}
		}
		else
		{
			float xpLeft = nextLevelXp - playerXp;
			float actionsRemaining = xpLeft / actionXp;
			if (actionsRemaining <= 0)
			{
				return 0;
			}
			else
			{
				return (int) actionsRemaining;
			}
		}

	}
}
