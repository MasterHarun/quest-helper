/*
 * Copyright (c) 2021, Zoinkwiz <https://github.com/Zoinkwiz>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
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
import com.questhelper.steps.DetailedSkillStep;
import com.questhelper.steps.ObjectStep;
import com.questhelper.steps.QuestStep;
import com.questhelper.steps.SkillStep;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import lombok.Getter;
import net.runelite.api.ItemID;
import net.runelite.api.Skill;
import net.runelite.api.coords.WorldPoint;
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

	SkillRequirement fl1, fl5, fl10, fl20, fl25, fl35, fl40, fll40, fl50, fl55, fl65, fl70, fl80, fl85, fl92;

	QuestStep acquireKnife;
	QuestStep fletchArrowShafts;

	QuestStep fletchShortBows, fletchLongBows, fletchOakShort, fletchOakLong, fletchWillowShort, fletchWillowLong,
		fletchMapleShort, fletchMapleLong, fletchYewShort, fletchYewLong, fletchMagicShort, fletchMagicLong;

	QuestStep fletchBattlestaves, fletchRedwoodShields;

	@Override
	public QuestStep loadStep()
	{

		setupRequirements();
		setupSteps();

		SkillStep fullTraining = new SkillStep(this, acquireKnife);

		fullTraining.addStep(fl92, fletchRedwoodShields);
		fullTraining.addStep(fl85, fletchMagicLong);
		fullTraining.addStep(fl80, fletchMagicShort);
		fullTraining.addStep(fl70, fletchYewLong);
		fullTraining.addStep(fl65, fletchYewShort);
		fullTraining.addStep(fl55, fletchMapleLong);
		fullTraining.addStep(fl50, fletchMapleShort);
		fullTraining.addStep(fll40, fletchBattlestaves);
		fullTraining.addStep(fl40, fletchWillowLong);
		fullTraining.addStep(fl35, fletchWillowShort);
		fullTraining.addStep(fl25, fletchOakLong);
		fullTraining.addStep(fl20, fletchOakShort);
		fullTraining.addStep(fl10, fletchLongBows);
		fullTraining.addStep(fl5, fletchShortBows);

		return fullTraining;
	}

	private void setupRequirements()
	{
		fl1 = new SkillRequirement(Skill.FLETCHING, 1);
		fl5 = new SkillRequirement(Skill.FLETCHING, 5);
		fl10 = new SkillRequirement(Skill.FLETCHING, 10);
		fl20 = new SkillRequirement(Skill.FLETCHING, 20);
		fl25 = new SkillRequirement(Skill.FLETCHING, 25);
		fl35 = new SkillRequirement(Skill.FLETCHING, 35);
		fll40 = new SkillRequirement(Skill.FLETCHING, 40);
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
		Skill skill = Skill.FLETCHING;
		acquireKnife = new ObjectStep(this, ItemID.KNIFE, "Get a new knife");

		fletchArrowShafts = new DetailedSkillStep(this, "Fletch Arrow shafts to 5.", skill, 1, 5,
			FletchingAction.ARROW_SHAFT.getXp() * 15,
			knife, logs);

		fletchShortBows = new DetailedSkillStep(this, "5 - 10 Shortbows.", skill, 5, 10,
			FletchingAction.SHORTBOW.getXp(),
			knife, logs, fl5);

		fletchLongBows = new DetailedSkillStep(this, "10 - 20 Longbows.", skill, 10, 20,
			FletchingAction.LONGBOW.getXp(),
			knife, logs, bowString.quantity(-1), fl10);

		fletchOakShort = new DetailedSkillStep(this, "20 - 25 Oak Shortbows.", skill, 20, 25,
			FletchingAction.OAK_SHORTBOW.getXp(),
			knife, oakLogs, bowString.quantity(-1), fl20);

		fletchOakLong = new DetailedSkillStep(this, "25 - 35 Oak Longbows. ", skill, 25, 35,
			FletchingAction.OAK_LONGBOW.getXp(), knife, oakLogs, bowString.quantity(-1), fl25);

		fletchWillowShort = new DetailedSkillStep(this, "35 - 40 Willow Shortbows.", skill, 35, 40,
			FletchingAction.WILLOW_SHORTBOW.getXp(),
			knife, willowLogs, bowString.quantity(-1), fl35);

		fletchWillowLong = new DetailedSkillStep(this, "40 - 50 Willow Longbows", skill, 40, 50,
			FletchingAction.WILLOW_LONGBOW.getXp(),
			knife, willowLogs, bowString.quantity(-1), fl40);

		fletchMapleShort = new DetailedSkillStep(this, "50 - 55 Maple Shortbows.", skill, 50, 55,
			FletchingAction.MAPLE_SHORTBOW.getXp(),
			knife, mapleLogs, bowString.quantity(-1), fl50);

		fletchMapleLong = new DetailedSkillStep(this, "55 - 65 Maple Longbows.", skill, 55, 65,
			FletchingAction.MAPLE_LONGBOW.getXp(),
			knife, mapleLogs, bowString.quantity(-1), fl55);

		fletchYewShort = new DetailedSkillStep(this, "65 - 70 Yew Shortbows.", skill, 65, 70,
			FletchingAction.YEW_SHORTBOW.getXp(),
			knife, yewLogs, bowString.quantity(-1), fl65);

		fletchYewLong = new DetailedSkillStep(this, "70 - 80 Yew Longbows.", skill, 70, 80,
			FletchingAction.YEW_LONGBOW.getXp(),
			knife, yewLogs, bowString.quantity(-1), fl70);

		fletchMagicShort = new DetailedSkillStep(this, "80 - 85 Magic Shortbows.", skill, 80, 85,
			FletchingAction.MAGIC_SHORTBOW.getXp(),
			knife, magicLogs, bowString.quantity(-1), fl80);

		fletchMagicLong = new DetailedSkillStep(this, "85 - 99 Magic Longbows.", skill, 85, 99,
			FletchingAction.MAGIC_LONGBOW.getXp(),
			knife, magicLogs, bowString.quantity(-1), fl85);

		fletchBattlestaves = new DetailedSkillStep(this, "40 - 80 Battlestaves.", skill, 40, 80,
			FletchingAction.BATTLESTAFF.getXp(),
			knife, celastrusBarks, fl40);

		fletchRedwoodShields = new DetailedSkillStep(this, "92 - 99 Redwood Shields.", skill, 92, 99,
			FletchingAction.REDWOOD_SHIELD.getXp(),
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

		allSteps.add(new PanelDetails("Get a knife", Collections.singletonList(acquireKnife), knife));

		if (knife.check(client))
		{
			allSteps.get(0).setHideCondition(knife);
		}

		allSteps.add(new PanelDetails("1 - 5/10 Fletch Arrow shafts", Collections.singletonList(fletchArrowShafts),
			knife, logs));

		allSteps.add(new PanelDetails("5 - 99 Fletch Bows", Arrays.asList(fletchShortBows, fletchLongBows,
			fletchOakShort, fletchOakLong, fletchWillowShort, fletchWillowLong, fletchMapleShort, fletchMapleLong,
			fletchYewShort, fletchYewLong, fletchMagicShort, fletchMagicLong),
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
