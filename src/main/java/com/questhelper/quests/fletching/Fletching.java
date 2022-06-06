package com.questhelper.quests.fletching;

import com.questhelper.QuestDescriptor;
import com.questhelper.QuestHelperQuest;
import com.questhelper.panel.PanelDetails;
import com.questhelper.questhelpers.ComplexStateQuestHelper;
import com.questhelper.requirements.conditional.Conditions;
import com.questhelper.requirements.item.ItemRequirement;
import com.questhelper.requirements.player.SkillRequirement;
import com.questhelper.requirements.util.ActionsLeft;
import com.questhelper.requirements.util.LogicType;
import com.questhelper.rewards.UnlockReward;
import com.questhelper.steps.ConditionalStep;
import com.questhelper.steps.DetailedQuestStep;
import com.questhelper.steps.QuestStep;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import net.runelite.api.ItemID;
import net.runelite.api.Skill;
import net.runelite.client.plugins.skillcalculator.skills.FletchingAction;

@QuestDescriptor(
	quest = QuestHelperQuest.FLETCHING
)
public class Fletching extends ComplexStateQuestHelper
{
	//Items Required
	ItemRequirement knife, logs;
	ItemRequirement bowString, oakLogs, willowLogs, mapleLogs, yewLogs, magicLogs;

	ItemRequirement celastrusBarks, redwoodLogs;

	SkillRequirement fl5, fl10, fl20, fl25, fl35, fl40, fl50, fl55, fl65, fl70, fl80, fl85, fl92;

	QuestStep fletchArrowShafts;

	QuestStep fletchShortBows, fletchLongBows, fletchOakShort, fletchOakLong, fletchWillowShort, fletchWillowLong,
		fletchMapleShort, fletchMapleLong, fletchYewShort, fletchYewLong, fletchMagicShort, fletchMagicLong;

	QuestStep fletchBattlestaves, fletchRedwoodShields;

	@Override
	public QuestStep loadStep()
	{
		setupRequirements();
		setupSteps();

		ConditionalStep fullTraining = new ConditionalStep(this, fletchArrowShafts);
		if (redwoodLogs.check(client))
		{
			fullTraining.addStep(fl92, fletchRedwoodShields);
		}
		else
		{
			fullTraining.addStep(fl85, fletchMagicLong);
		}
		fullTraining.addStep(fl80, fletchMagicShort);
		fullTraining.addStep(fl70, fletchYewLong);
		fullTraining.addStep(fl65, fletchYewShort);
		fullTraining.addStep(fl55, fletchMapleLong);
		fullTraining.addStep(fl50, fletchMapleShort);
		if (celastrusBarks.check(client))
		{
			fullTraining.addStep(fl40, fletchBattlestaves);
		}
		else
		{
			fullTraining.addStep(fl40, fletchWillowLong);
		}
		fullTraining.addStep(fl35, fletchWillowShort);
		fullTraining.addStep(fl25, fletchOakLong);
		fullTraining.addStep(fl20, fletchOakShort);
		fullTraining.addStep(fl10, fletchLongBows);
		fullTraining.addStep(fl5, fletchShortBows);


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
		fl92 = new SkillRequirement(Skill.FLETCHING, 92);

		knife = new ItemRequirement("Knife", ItemID.KNIFE);
		knife = knife.alsoCheckBank(questBank);

		bowString = new ItemRequirement("Bow String", ItemID.BOW_STRING).showConditioned(
			new Conditions(fl5)
		);
		bowString = bowString.alsoCheckBank(questBank);

		logs = new ItemRequirement("Logs", ItemID.LOGS).showConditioned(
			new Conditions(fl5, new Conditions(LogicType.NOR, fl20))
		);
		logs = logs.alsoCheckBank(questBank);

		oakLogs = new ItemRequirement("Oak Logs", ItemID.OAK_LOGS).showConditioned(
			new Conditions(fl20, new Conditions(LogicType.NOR, fl35))
		);
		oakLogs = oakLogs.alsoCheckBank(questBank);

		willowLogs = new ItemRequirement("Willow Logs", ItemID.WILLOW_LOGS).showConditioned(
			new Conditions(fl35, new Conditions(LogicType.NOR, fl50))
		);
		willowLogs = willowLogs.alsoCheckBank(questBank);

		mapleLogs = new ItemRequirement("Maple Logs", ItemID.MAPLE_LOGS).showConditioned(
			new Conditions(fl50, new Conditions(LogicType.NOR, fl65))
		);
		mapleLogs = mapleLogs.alsoCheckBank(questBank);

		yewLogs = new ItemRequirement("Yew Logs", ItemID.YEW_LOGS).showConditioned(
			new Conditions(fl65, new Conditions(LogicType.NOR, fl80))
		);
		yewLogs = yewLogs.alsoCheckBank(questBank);

		magicLogs = new ItemRequirement("Magic Logs", ItemID.MAGIC_LOGS).showConditioned(
			new Conditions(fl80)
		);
		magicLogs = magicLogs.alsoCheckBank(questBank);

		redwoodLogs = new ItemRequirement("Redwood Logs", ItemID.REDWOOD_LOGS).showConditioned(
			new Conditions(fl92)
		);
		redwoodLogs = redwoodLogs.alsoCheckBank(questBank);

		celastrusBarks = new ItemRequirement("Celastrus Bark", ItemID.CELASTRUS_BARK).showConditioned(
			new Conditions(fl40, new Conditions(LogicType.NOR, fl80))
		);
		celastrusBarks = celastrusBarks.alsoCheckBank(questBank);

	}

	private void setupSteps()
	{
		float playerXp = client.getSkillExperience(Skill.FLETCHING);
		int skill = client.getRealSkillLevel(Skill.FLETCHING);

		fletchArrowShafts = new DetailedQuestStep(this, String.format("Fletch Arrow shafts to 5. x%s",
			ActionsLeft.get(playerXp, skill, 1, 5, FletchingAction.ARROW_SHAFT.getXp() * 15)),
			knife, logs);

		fletchShortBows = new DetailedQuestStep(this, String.format("5 - 10 Shortbows. x%s",
			ActionsLeft.get(playerXp, skill, 5, 10, FletchingAction.SHORTBOW.getXp())),
			knife, logs, fl5);

		fletchLongBows = new DetailedQuestStep(this, String.format("10 - 20 Longbows. x%s",
			ActionsLeft.get(playerXp, skill, 10, 20, FletchingAction.LONGBOW.getXp())),
			knife, logs, bowString.quantity(-1), fl10);

		fletchOakShort = new DetailedQuestStep(this, String.format("20 - 25 Oak Shortbows. x%s",
			ActionsLeft.get(playerXp, skill, 20, 25, FletchingAction.OAK_SHORTBOW.getXp())),
			knife, oakLogs, bowString.quantity(-1), fl20);

		fletchOakLong = new DetailedQuestStep(this, String.format("25 - 35 Oak Longbows. x%s",
			ActionsLeft.get(playerXp, skill, 25, 35, FletchingAction.OAK_LONGBOW.getXp())),
			knife, oakLogs.quantity(-1), bowString.quantity(-1), fl25);

		fletchWillowShort = new DetailedQuestStep(this, String.format("35 - 40 Willow Shortbows. x%s",
			ActionsLeft.get(playerXp, skill, 35, 40, FletchingAction.WILLOW_SHORTBOW.getXp())),
			knife, willowLogs, bowString.quantity(-1), fl35);

		fletchWillowLong = new DetailedQuestStep(this, String.format("40 - 50 Willow Longbows. x%s",
			ActionsLeft.get(playerXp, skill, 40, 50, FletchingAction.WILLOW_LONGBOW.getXp())),
			knife, willowLogs, bowString.quantity(-1), fl40);

		fletchMapleShort = new DetailedQuestStep(this, String.format("50 - 55 Maple Shortbows. x%s",
			ActionsLeft.get(playerXp, skill, 50, 55, FletchingAction.MAPLE_SHORTBOW.getXp())),
			knife, mapleLogs, bowString.quantity(-1), fl50);

		fletchMapleLong = new DetailedQuestStep(this, String.format("55 - 65 Maple Longbows. x%s",
			ActionsLeft.get(playerXp, skill, 55, 65, FletchingAction.MAPLE_LONGBOW.getXp())),
			knife, mapleLogs, bowString.quantity(-1), fl55);

		fletchYewShort = new DetailedQuestStep(this, String.format("65 - 70 Yew Shortbows. x%s",
			ActionsLeft.get(playerXp, skill, 65, 70, FletchingAction.YEW_SHORTBOW.getXp())),
			knife, yewLogs, bowString.quantity(-1), fl65);

		fletchYewLong = new DetailedQuestStep(this, String.format("70 - 80 Yew Longbows. x%s",
			ActionsLeft.get(playerXp, skill, 70, 80, FletchingAction.YEW_LONGBOW.getXp())),
			knife, yewLogs, bowString.quantity(-1), fl70);

		fletchMagicShort = new DetailedQuestStep(this, String.format("80 - 85 Magic Shortbows. x%s",
			ActionsLeft.get(playerXp, skill, 80, 85, FletchingAction.MAGIC_SHORTBOW.getXp())),
			knife, magicLogs, bowString.quantity(-1), fl80);

		fletchMagicLong = new DetailedQuestStep(this, String.format("85 - 99 Magic Longbows. x%s",
			ActionsLeft.get(playerXp, skill, 85, 99, FletchingAction.MAGIC_LONGBOW.getXp())),
			knife, magicLogs, bowString.quantity(-1), fl85);

		fletchBattlestaves = new DetailedQuestStep(this, String.format("40 - 80 Battlestaves. x%s",
			ActionsLeft.get(playerXp, skill, 40, 80, FletchingAction.BATTLESTAFF.getXp())),
			knife, celastrusBarks, fl40);

		fletchRedwoodShields = new DetailedQuestStep(this, String.format("92 - 99 Redwood Shields. x%s",
			ActionsLeft.get(playerXp, skill, 92, 99, FletchingAction.REDWOOD_SHIELD.getXp())),
			knife, redwoodLogs, fl92);
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
		return Arrays.asList(knife, logs, bowString, oakLogs, willowLogs, mapleLogs, yewLogs, magicLogs, redwoodLogs, celastrusBarks);
	}

	@Override
	public List<PanelDetails> getPanels()
	{
		List<PanelDetails> allSteps = new ArrayList<>();

		allSteps.add(new PanelDetails("1 - 5/10 Fletch Arrow shafts", Collections.singletonList(fletchArrowShafts),
			knife, logs));

		allSteps.add(new PanelDetails("5 - 99 Fletch Bows", Arrays.asList(fletchShortBows, fletchLongBows,
			fletchOakShort, fletchOakLong, fletchWillowShort, fletchWillowLong, fletchMapleShort, fletchMapleLong,
			fletchYewShort, fletchYewLong, fletchMagicShort, fletchMagicLong
		),
			knife, logs, bowString, oakLogs, willowLogs, mapleLogs, yewLogs, magicLogs));

		allSteps.add(new PanelDetails("40 - 80 Battlestaves", Collections.singletonList(fletchBattlestaves),
			new SkillRequirement(Skill.FLETCHING, 40),
			knife, celastrusBarks));

		allSteps.add(new PanelDetails("92 - 99 Redwood Shields", Collections.singletonList(fletchRedwoodShields),
			new SkillRequirement(Skill.FLETCHING, 92),
			knife, redwoodLogs));

		return allSteps;
	}
}
