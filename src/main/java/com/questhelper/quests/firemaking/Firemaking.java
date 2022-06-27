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
package com.questhelper.quests.firemaking;

import com.questhelper.QuestHelperQuest;
import com.questhelper.questhelpers.ComplexStateQuestHelper;
import com.questhelper.requirements.conditional.Conditions;
import com.questhelper.requirements.player.SkillRequirement;
import com.questhelper.requirements.util.LogicType;
import com.questhelper.rewards.UnlockReward;
import com.questhelper.steps.DetailedSkillStep;
import com.questhelper.steps.ObjectStep;
import com.questhelper.steps.SkillStep;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import net.runelite.api.Item;
import net.runelite.api.ItemID;
import com.questhelper.requirements.item.ItemRequirement;
import com.questhelper.panel.PanelDetails;
import com.questhelper.steps.QuestStep;
import com.questhelper.QuestDescriptor;
import net.runelite.api.ObjectID;
import net.runelite.api.Skill;
import net.runelite.api.coords.WorldPoint;
import net.runelite.client.plugins.skillcalculator.skills.FiremakingAction;

@QuestDescriptor(
	quest = QuestHelperQuest.FIREMAKING
)
public class Firemaking extends ComplexStateQuestHelper
{
	ItemRequirement tinderBox, logs, oakLogs, willowLogs, teakLogs, arcticPine, mapleLogs, mahoganyLogs, yewLogs,
		magicLogs, redwoodLogs;

	ItemRequirement pyromancerHood, pyromancerGarb, pyromancerRobe, pyromancerBoots;
	SkillRequirement fm1, fm15, fm30, fm35, fm42, fm45, fm50, fm60, fm75, fm90, fm99;

	QuestStep acquireTinderbox;

	QuestStep burnLogs, burnOaks, burnWillow, burnTeak, burnPine, burnMaple, burnMahogany, burnYews, burnMagic, burnRedwood;


	@Override
	public QuestStep loadStep()
	{
		setupRequirements();
		setupSteps();

		SkillStep fullTraining = new SkillStep(this, acquireTinderbox);

		fullTraining.addStep(fm90, burnRedwood);
		fullTraining.addStep(fm75, burnMagic);
		fullTraining.addStep(fm60, burnYews);
		fullTraining.addStep(fm50, burnMahogany);
		fullTraining.addStep(fm45, burnMaple);
		fullTraining.addStep(fm42, burnPine);
		fullTraining.addStep(fm35, burnTeak);
		fullTraining.addStep(fm30, burnWillow);
		fullTraining.addStep(fm15, burnOaks);
		fullTraining.addStep(fm1, burnLogs);


		return fullTraining;
	}

	public void setupRequirements()
	{

		fm1 = new SkillRequirement(Skill.FIREMAKING, 1);
		fm15 = new SkillRequirement(Skill.FIREMAKING, 15);
		fm30 = new SkillRequirement(Skill.FIREMAKING, 30);
		fm35 = new SkillRequirement(Skill.FIREMAKING, 35);
		fm42 = new SkillRequirement(Skill.FIREMAKING, 42);
		fm45 = new SkillRequirement(Skill.FIREMAKING, 45);
		fm50 = new SkillRequirement(Skill.FIREMAKING, 50);
		fm60 = new SkillRequirement(Skill.FIREMAKING, 60);
		fm75 = new SkillRequirement(Skill.FIREMAKING, 75);
		fm90 = new SkillRequirement(Skill.FIREMAKING, 90);
		fm99 = new SkillRequirement(Skill.FIREMAKING, 99);

		tinderBox = new ItemRequirement("Tinderbox", ItemID.TINDERBOX);
		tinderBox = tinderBox.alsoCheckBank(questBank);

		logs = new ItemRequirement("Logs", ItemID.LOGS).showConditioned(
			new Conditions(fm1, new Conditions(LogicType.NOR, fm15)));
		logs = logs.alsoCheckBank(questBank);

		oakLogs = new ItemRequirement("Oak Logs", ItemID.OAK_LOGS).showConditioned(
			new Conditions(fm15, new Conditions(LogicType.NOR, fm30)));
		oakLogs = oakLogs.alsoCheckBank(questBank);

		willowLogs = new ItemRequirement("Willow Logs", ItemID.WILLOW_LOGS).showConditioned(
			new Conditions(fm30, new Conditions(LogicType.NOR, fm35)));
		willowLogs = willowLogs.alsoCheckBank(questBank);

		teakLogs = new ItemRequirement("Maple Logs", ItemID.TEAK_LOGS).showConditioned(
			new Conditions(fm35, new Conditions(LogicType.NOR, fm42)));
		teakLogs = teakLogs.alsoCheckBank(questBank);

		arcticPine = new ItemRequirement("Arctic Pine Logs", ItemID.ARCTIC_PINE_LOGS).showConditioned(
			new Conditions(fm42, new Conditions(LogicType.NOR, fm45)));
		arcticPine = arcticPine.alsoCheckBank(questBank);

		mapleLogs = new ItemRequirement("Maple Logs", ItemID.MAPLE_LOGS).showConditioned(
			new Conditions(fm45, new Conditions(LogicType.NOR, fm50)));
		mapleLogs = mapleLogs.alsoCheckBank(questBank);

		mahoganyLogs = new ItemRequirement("Mahogany Logs", ItemID.MAHOGANY_LOGS).showConditioned(
			new Conditions(fm50, new Conditions(LogicType.NOR, fm60)));
		mahoganyLogs = mahoganyLogs.alsoCheckBank(questBank);

		yewLogs = new ItemRequirement("Yew Logs", ItemID.YEW_LOGS).showConditioned(
			new Conditions(fm60, new Conditions(LogicType.NOR, fm75)));
		yewLogs = yewLogs.alsoCheckBank(questBank);

		magicLogs = new ItemRequirement("Magic Logs", ItemID.MAGIC_LOGS).showConditioned(
			new Conditions(fm75, new Conditions(LogicType.NOR, fm90)));
		magicLogs = magicLogs.alsoCheckBank(questBank);

		redwoodLogs = new ItemRequirement("Redwood Logs", ItemID.REDWOOD_LOGS).showConditioned(
			new Conditions(fm90, new Conditions(LogicType.NOR, fm99)));
		redwoodLogs = redwoodLogs.alsoCheckBank(questBank);

		pyromancerHood = new ItemRequirement("Pyromancer Hood", ItemID.PYROMANCER_HOOD);
		pyromancerHood = pyromancerHood.showConditioned(pyromancerHood.alsoCheckBank(questBank));

		pyromancerGarb = new ItemRequirement("Pyromancer Garb", ItemID.PYROMANCER_GARB);
		pyromancerGarb = pyromancerGarb.showConditioned(pyromancerGarb.alsoCheckBank(questBank));

		pyromancerRobe = new ItemRequirement("Pyromancer Robe", ItemID.PYROMANCER_ROBE);
		pyromancerRobe = pyromancerRobe.showConditioned(pyromancerRobe.alsoCheckBank(questBank));

		pyromancerBoots = new ItemRequirement("Pyromancer Boots", ItemID.PYROMANCER_BOOTS);
		pyromancerBoots = pyromancerBoots.showConditioned(pyromancerBoots.alsoCheckBank(questBank));

	}

	private void setupSteps()
	{
		Skill skill = Skill.FIREMAKING;

		acquireTinderbox = new ObjectStep(this, ItemID.TINDERBOX, "Get a tinderbox", fm1);

		burnLogs = new DetailedSkillStep(this, "1 - 15 Normal Logs.", skill, 1, 15,
			FiremakingAction.LOGS.getXp(),
			tinderBox, logs, fm1);

		burnOaks = new DetailedSkillStep(this, "15 - 30 Oak Logs.", skill, 15, 30,
			FiremakingAction.OAK_LOGS.getXp(),
			tinderBox, oakLogs, fm15);

		burnWillow = new DetailedSkillStep(this, "30 - 35 Willow Logs.", skill, 30, 35,
			FiremakingAction.WILLOW_LOGS.getXp(),
			tinderBox, willowLogs, fm30);

		burnTeak = new DetailedSkillStep(this, "35 - 42 Teak Logs.", skill, 35, 42,
			FiremakingAction.TEAK_LOGS.getXp(),
			tinderBox, teakLogs, fm35);

		burnPine = new DetailedSkillStep(this, "42 - 45 Arctic Pine Logs.", skill, 42, 45,
			FiremakingAction.ARCTIC_PINE_LOGS.getXp(),
			tinderBox, arcticPine, fm42);

		burnMaple = new DetailedSkillStep(this, "45 - 50 Maple Logs.", skill, 45, 50,
			FiremakingAction.MAPLE_LOGS.getXp(),
			tinderBox, mapleLogs, fm45);

		burnMahogany = new DetailedSkillStep(this, "50 - 60 Mahogany Logs.", skill, 50, 60,
			FiremakingAction.MAHOGANY_LOGS.getXp(),
			tinderBox, mahoganyLogs, fm50);

		burnYews = new DetailedSkillStep(this, "60 - 75 Yew Logs.", skill, 60, 75,
			FiremakingAction.YEW_LOGS.getXp(),
			tinderBox, yewLogs, fm60);

		burnMagic = new DetailedSkillStep(this, "75 - 90 Magic Logs.", skill, 75, 90,
			FiremakingAction.MAGIC_LOGS.getXp(),
			tinderBox, magicLogs, fm75);

		burnRedwood = new DetailedSkillStep(this, "90 - 99 Redwood Logs.", skill, 90, 99,
			FiremakingAction.REDWOOD_LOGS.getXp(),
			tinderBox, redwoodLogs, fm90);

	}

	@Override
	public List<UnlockReward> getUnlockRewards()
	{
		return Collections.singletonList(
			new UnlockReward("Ability to purchase Firemaking Cape for 99k")
		);
	}

	@Override
	public List<ItemRequirement> getItemRequirements()
	{
		return Arrays.asList(tinderBox, logs, oakLogs, willowLogs, teakLogs, arcticPine, mapleLogs, mahoganyLogs, yewLogs,
			magicLogs, redwoodLogs);
	}

	@Override
	public List<ItemRequirement> getItemRecommended()
	{
		return Arrays.asList(pyromancerHood, pyromancerGarb, pyromancerRobe, pyromancerBoots);
	}

	@Override
	public List<PanelDetails> getPanels()
	{
		List<PanelDetails> allSteps = new ArrayList<>();

		allSteps.add(new PanelDetails("Burn Logs", Arrays.asList(burnLogs, burnOaks, burnWillow, burnTeak, burnPine,
			burnMaple, burnMahogany, burnYews, burnMagic, burnRedwood),
			tinderBox, logs, oakLogs, willowLogs, teakLogs, arcticPine, mapleLogs, mahoganyLogs, yewLogs,
			magicLogs, redwoodLogs, pyromancerHood, pyromancerGarb, pyromancerRobe, pyromancerBoots));

		return allSteps;
	}
}
