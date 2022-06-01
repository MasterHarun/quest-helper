package com.questhelper.quests.fletching;

import com.questhelper.QuestDescriptor;
import com.questhelper.QuestHelperQuest;
import com.questhelper.panel.PanelDetails;
import com.questhelper.questhelpers.ComplexStateQuestHelper;
import com.questhelper.requirements.conditional.Conditions;
import com.questhelper.requirements.item.ItemRequirement;
import com.questhelper.requirements.player.SkillRequirement;
import com.questhelper.requirements.util.LogicType;
import com.questhelper.rewards.UnlockReward;
import com.questhelper.steps.ConditionalStep;
import com.questhelper.steps.DetailedQuestStep;
import com.questhelper.steps.ObjectStep;
import com.questhelper.steps.QuestStep;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import net.runelite.api.Item;
import net.runelite.api.ItemID;
import net.runelite.api.ObjectID;
import net.runelite.api.Skill;
import net.runelite.api.coords.WorldPoint;

@QuestDescriptor(
	quest = QuestHelperQuest.FLETCHING
)
public class Fletching extends ComplexStateQuestHelper
{
	//Items Required
	ItemRequirement knife, logs, bowString, oakLogs, willowLogs, mapleLogs, yewLogs, magicLogs;

	// Items recommended
//	ItemRequirement lumberjackHat, lumberjackBody, lumberjackLegs, lumberjackBoots;

	SkillRequirement fl5, fl10, fl20, fl25, fl35, fl40, fl50, fl55, fl65, fl70, fl80, fl85;

	QuestStep fletchArrowShafts;

	QuestStep fletchShortBows, fletchLongBows, fletchOakShort, fletchOakLong, fletchWillowShort, fletchWillowLong,
		fletchMapleShort, fletchMapleLong, fletchYewShort, fletchYewLong, fletchMagicShort, fletchMagicLong;

	int skillReq = 0;

	@Override
	public QuestStep loadStep()
	{
		setupRequirements();
		setupSteps();

		int s = client.getRealSkillLevel(Skill.FLETCHING);

		ConditionalStep fullTraining = new ConditionalStep(this, fletchArrowShafts);
		// if certain item is in player inventory switch from bow training to other. for redwood shields*
		if (SkillRequirement.skillIsBetween(s, 5, 10))
		{
			fullTraining.addStep(fl5, fletchShortBows);
			skillReq = 5;
		}
		else if (SkillRequirement.skillIsBetween(s, 10, 20))
		{
			fullTraining.addStep(fl10, fletchLongBows);
			skillReq = 10;

		}
		else if (SkillRequirement.skillIsBetween(s, 20, 25))
		{
			fullTraining.addStep(fl20, fletchOakShort);
			skillReq = 20;
		}
		else if (SkillRequirement.skillIsBetween(s, 25, 35))
		{
			fullTraining.addStep(fl25, fletchOakLong);
			skillReq = 25;
		}
		else if (SkillRequirement.skillIsBetween(s, 35, 40))
		{
			fullTraining.addStep(fl35, fletchWillowShort);
			skillReq = 35;
		}
		else if (SkillRequirement.skillIsBetween(s, 40, 50))
		{
			fullTraining.addStep(fl40, fletchWillowLong);
			skillReq = 40;
		}
		else if (SkillRequirement.skillIsBetween(s, 50, 55))
		{
			fullTraining.addStep(fl50, fletchMapleShort);
			skillReq = 50;
		}
		else if (SkillRequirement.skillIsBetween(s, 55, 65))
		{
			fullTraining.addStep(fl55, fletchMapleLong);
			skillReq = 55;
		}
		else if (SkillRequirement.skillIsBetween(s, 65, 70))
		{
			fullTraining.addStep(fl65, fletchYewShort);
			skillReq = 65;
		}
		else if (SkillRequirement.skillIsBetween(s, 70, 80))
		{
			fullTraining.addStep(fl70, fletchYewLong);
			skillReq = 70;
		}
		else if (SkillRequirement.skillIsBetween(s, 80, 85))
		{
			fullTraining.addStep(fl80, fletchMagicShort);
			skillReq = 80;
		}
		else if (SkillRequirement.skillIsBetween(s, 85, 99))
		{
			fullTraining.addStep(fl85, fletchMagicLong);
			skillReq = 85;
		}
		else
		{
			fullTraining.addStep(fl5, fletchShortBows);
		}


		return fullTraining;
	}

	private void setupRequirements()
	{
		fl5 = new SkillRequirement(Skill.FLETCHING, 5);
		fl10 = new SkillRequirement(Skill.FLETCHING, 10);
		fl20 = new SkillRequirement(Skill.FLETCHING, 20);
		fl25 = new SkillRequirement(Skill.FLETCHING, 25);
		fl35 = new SkillRequirement(Skill.FLETCHING, 35);
		fl40 = new SkillRequirement(Skill.FLETCHING, 40);
		fl50 = new SkillRequirement(Skill.FLETCHING, 50);
		fl55 = new SkillRequirement(Skill.FLETCHING, 55);
		fl65 = new SkillRequirement(Skill.FLETCHING, 65);
		fl70 = new SkillRequirement(Skill.FLETCHING, 70);
		fl80 = new SkillRequirement(Skill.FLETCHING, 80);
		fl85 = new SkillRequirement(Skill.FLETCHING, 85);


		knife = new ItemRequirement("Knife", ItemID.KNIFE);
		knife = knife.alsoCheckBank(questBank);

		bowString = new ItemRequirement("Bow String", ItemID.BOW_STRING).showConditioned(
			new Conditions(fl5)
		);

		logs = new ItemRequirement("Logs", ItemID.LOGS).showConditioned(
			new Conditions(fl5, new Conditions(LogicType.NOR, fl20))
		);

		oakLogs = new ItemRequirement("Oak Logs", ItemID.OAK_LOGS).showConditioned(
			new Conditions(fl20, new Conditions(LogicType.NOR, fl35))
		);

		willowLogs = new ItemRequirement("Willow Logs", ItemID.WILLOW_LOGS).showConditioned(
			new Conditions(fl35, new Conditions(LogicType.NOR, fl50))
		);

		mapleLogs = new ItemRequirement("Maple Logs", ItemID.MAPLE_LOGS).showConditioned(
			new Conditions(fl50, new Conditions(LogicType.NOR, fl65))
		);

		yewLogs = new ItemRequirement("Yew Logs", ItemID.YEW_LOGS).showConditioned(
			new Conditions(fl65, new Conditions(LogicType.NOR, fl80))
		);

		magicLogs = new ItemRequirement("Magic Logs", ItemID.MAGIC_LOGS).showConditioned(
			new Conditions(fl80)
		);


	}

	private void setupSteps()
	{
// todo: add way to calculate quantity needed for next step

		fletchArrowShafts = new DetailedQuestStep(this, "Fletch Arrow shafts to 5.",
			knife.highlighted(), logs.highlighted().quantity(78));

		fletchShortBows = new DetailedQuestStep(this, "5 - 10 Shortbows. x153", knife,
			logs, fl5);

		fletchLongBows = new DetailedQuestStep(this, "10 - 20 Longbows. x332", knife,
			logs, fl10);

		fletchOakShort = new DetailedQuestStep(this, "20 - 25 Oak Shortbows. x205", knife,
			oakLogs, fl20);

		fletchOakLong = new DetailedQuestStep(this, "25 - 35 Oak Longbows. x582", knife,
			oakLogs, fl25);

		fletchWillowShort = new DetailedQuestStep(this, "35 - 40 Willow Shortbows. x445", knife,
			willowLogs, fl35);

		fletchWillowLong = new DetailedQuestStep(this, "40 - 50 Willow Longbows. x1545", knife,
			willowLogs, fl40);

		fletchMapleShort = new DetailedQuestStep(this, "50 - 55 Maple Shortbows. x1306", knife,
			mapleLogs, fl50);

		fletchMapleLong = new DetailedQuestStep(this, "55 - 65 Maple Longbows. x4851", knife,
			mapleLogs, fl55);

		fletchYewShort = new DetailedQuestStep(this, "65 - 70 Yew Shortbows. x4269", knife,
			yewLogs, fl65);

		fletchYewLong = new DetailedQuestStep(this, "70 - 80 Yew Longbows. x16646", knife,
			yewLogs, fl70);

		fletchMagicShort = new DetailedQuestStep(this, "80 - 85 Magic Shortbows. x15277", knife,
			magicLogs, fl80);

		fletchMagicLong = new DetailedQuestStep(this, "85 - 92/99 Magic Longbows. x106840", knife,
			magicLogs, fl85);

	}

	@Override
	public List<UnlockReward> getUnlockRewards()
	{
		return Collections.singletonList(
			new UnlockReward("Ability to purchase Fletching Cape for 99k")
		);
	}

	@Override
	public List<ItemRequirement> getItemRequirements()
	{
		return Arrays.asList(knife, logs, bowString, oakLogs, willowLogs, mapleLogs, yewLogs, magicLogs);
	}

//	@Override
//	public List<ItemRequirement> getItemRecommended()
//	{
//		return Collections.singletonList();
//	}

	@Override
	public List<PanelDetails> getPanels()
	{
		List<PanelDetails> allSteps = new ArrayList<>();

		allSteps.add(new PanelDetails("1 - 5/10 Fletch Arrow shafts", Collections.singletonList(fletchArrowShafts),
			knife, logs.quantity(78)));

		allSteps.add(new PanelDetails("5 - 99 Fletch Bows", Arrays.asList(fletchShortBows, fletchLongBows,
			fletchOakShort, fletchOakLong, fletchWillowShort, fletchWillowLong, fletchMapleShort, fletchMapleLong,
			fletchYewShort, fletchYewLong, fletchMagicShort, fletchMagicLong
			),
			new SkillRequirement(Skill.FLETCHING, skillReq),
			knife, logs, bowString, oakLogs, willowLogs, mapleLogs, yewLogs, magicLogs));

//		allSteps.add(new PanelDetails("40 - 80 Battlestaves",));
//		allSteps.add(new PanelDetails("92 - 99 Redwood Shields"))
		return allSteps;
	}
}
