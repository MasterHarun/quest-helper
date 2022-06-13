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
package com.questhelper.quests.mining;

import com.questhelper.QuestHelperQuest;
import com.questhelper.questhelpers.ComplexStateQuestHelper;
import com.questhelper.requirements.Requirement;
import com.questhelper.requirements.conditional.Conditions;
import com.questhelper.requirements.player.Favour;
import com.questhelper.requirements.player.FavourRequirement;
import com.questhelper.requirements.player.SkillRequirement;
import com.questhelper.requirements.quest.QuestRequirement;
import com.questhelper.requirements.util.LogicType;
import com.questhelper.rewards.UnlockReward;
import com.questhelper.steps.ConditionalStep;
import com.questhelper.steps.ObjectStep;
import com.questhelper.steps.SkillStep;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import net.runelite.api.ItemID;
import com.questhelper.requirements.item.ItemRequirement;
import com.questhelper.panel.PanelDetails;
import com.questhelper.steps.QuestStep;
import com.questhelper.QuestDescriptor;
import net.runelite.api.ObjectID;
import net.runelite.api.Skill;
import net.runelite.api.coords.WorldPoint;

@QuestDescriptor(
	quest = QuestHelperQuest.MINING
)
public class Mining extends ComplexStateQuestHelper
{
	// Items required
	ItemRequirement ironPickAxe, steelPickAxe, blackPickAxe, mithrilPickAxe, adamantPickAxe, runePickAxe, dragonPickAxe;

	// Quests required
	QuestRequirement shiloVillage, boneVoyage;
	// Items recommended
	ItemRequirement prospectorHelmet, prospectorJacket, prospectorLegs, prospectorBoots, amuletGlory, karamjaGloves3, varrockArmour4, expertMiningGloves, chisel, tinderbox, dynamite;

	SkillRequirement m1, m6, m11, m21, m31, m41, m61;

	SkillRequirement m15, m30, m40, m45, m70, m75, m92;

	Requirement lovakengj;

	ObjectStep acquireNewPickaxe, mineCopperTinOre, mineIronOre, mineMotherlode, mineGranite, mineGem, mineVolcanic, mineBlast, mineAmethyst;

	private int skillLvl;

	private String name;

	private LinkedHashMap<SkillRequirement, ItemRequirement> tools;
	private ItemRequirement tool;
	private int toolId;

	@Override
	public QuestStep loadStep()
	{
		skillLvl = client.getRealSkillLevel(Skill.MINING);
		tools = new LinkedHashMap<>();
		setupRequirements();
		setupSteps();

		SkillStep fullTraining = new SkillStep(this, acquireNewPickaxe);

		fullTraining.addStep(m92, mineAmethyst);
		fullTraining.addStep(m75, mineBlast);
		fullTraining.addStep(m70, mineVolcanic);
		fullTraining.addStep(m45, mineGranite);
		fullTraining.addStep(m40, mineGem);
		fullTraining.addStep(m30, mineMotherlode);
		fullTraining.addStep(m15, mineIronOre);
		fullTraining.addStep(m1, mineCopperTinOre);


		return fullTraining;
	}

	private void setupRequirements()
	{
		m1 = new SkillRequirement(Skill.MINING, 1);
		m6 = new SkillRequirement(Skill.MINING, 6);
		m11 = new SkillRequirement(Skill.MINING, 11);
		m21 = new SkillRequirement(Skill.MINING, 21);
		m31 = new SkillRequirement(Skill.MINING, 31);
		m41 = new SkillRequirement(Skill.MINING, 41);
		m61 = new SkillRequirement(Skill.MINING, 61);

		m15 = new SkillRequirement(Skill.MINING, 15);
		m30 = new SkillRequirement(Skill.MINING, 30);
		m40 = new SkillRequirement(Skill.MINING, 40);
		m45 = new SkillRequirement(Skill.MINING, 45);
		m70 = new SkillRequirement(Skill.MINING, 70);
		m75 = new SkillRequirement(Skill.MINING, 75);
		m92 = new SkillRequirement(Skill.MINING, 92);

		ironPickAxe = new ItemRequirement("Iron Pickaxe", ItemID.IRON_PICKAXE).showConditioned(
			new Conditions(LogicType.NOR, m6)
		);
		steelPickAxe = new ItemRequirement("Steel Pickaxe", ItemID.STEEL_PICKAXE).showConditioned(
			new Conditions(m6, new Conditions(LogicType.NOR, m11))
		);
		blackPickAxe = new ItemRequirement("Black Pickaxe", ItemID.BLACK_PICKAXE).showConditioned(
			new Conditions(m11, new Conditions(LogicType.NOR, m21))
		);
		mithrilPickAxe = new ItemRequirement("Mithril Pickaxe", ItemID.MITHRIL_PICKAXE).showConditioned(
			new Conditions(m21, new Conditions(LogicType.NOR, m31))
		);
		adamantPickAxe = new ItemRequirement("Adamant Pickaxe", ItemID.ADAMANT_PICKAXE).showConditioned(
			new Conditions(m31, new Conditions(LogicType.NOR, m41))
		);
		runePickAxe = new ItemRequirement("Rune Pickaxe", ItemID.RUNE_PICKAXE).showConditioned(
			new Conditions(m41, new Conditions(LogicType.NOR, m61))
		);
		dragonPickAxe = new ItemRequirement("Dragon Pickaxe", ItemID.DRAGON_PICKAXE).showConditioned(
			new Conditions(m61)
		);
		dragonPickAxe.addAlternatives(ItemID.RUNE_PICKAXE, "Rune Pickaxe");

		tools.put(m1,ironPickAxe);
		tools.put(m6, steelPickAxe);
		tools.put(m11, blackPickAxe);
		tools.put(m21, mithrilPickAxe);
		tools.put(m31, adamantPickAxe);
		tools.put(m41, runePickAxe);
		tools.put(m61, dragonPickAxe);

		tool = skillToolLvlChecker(skillLvl, tools);
		name = tool.getName();
		toolId = tool.getId();

		prospectorJacket = new ItemRequirement("Prospector Jacket", ItemID.PROSPECTOR_JACKET);
		prospectorJacket = prospectorJacket.showConditioned(prospectorJacket.alsoCheckBank(questBank));

		prospectorHelmet = new ItemRequirement("Prospector Helmet", ItemID.PROSPECTOR_HELMET);
		prospectorHelmet = prospectorHelmet.showConditioned(prospectorHelmet.alsoCheckBank(questBank));

		prospectorLegs = new ItemRequirement("Prospector legs", ItemID.PROSPECTOR_LEGS);
		prospectorLegs = prospectorLegs.showConditioned(prospectorLegs.alsoCheckBank(questBank));

		prospectorBoots = new ItemRequirement("Prospector boots", ItemID.PROSPECTOR_BOOTS);
		prospectorBoots = prospectorBoots.showConditioned(prospectorBoots.alsoCheckBank(questBank));

		amuletGlory = new ItemRequirement("Amulet of Glory", ItemID.AMULET_OF_GLORY);
		amuletGlory = amuletGlory.showConditioned(amuletGlory.alsoCheckBank(questBank));

		karamjaGloves3 = new ItemRequirement("Karamja gloves 3", ItemID.KARAMJA_GLOVES_3);
		karamjaGloves3 = amuletGlory.showConditioned(karamjaGloves3.alsoCheckBank(questBank));

		varrockArmour4 = new ItemRequirement("Varrock Armour 4", ItemID.VARROCK_ARMOUR_4);
		varrockArmour4 = varrockArmour4.showConditioned(varrockArmour4.alsoCheckBank(questBank));

		expertMiningGloves = new ItemRequirement("Expert Mining Gloves", ItemID.EXPERT_MINING_GLOVES);
		expertMiningGloves = expertMiningGloves.showConditioned(expertMiningGloves.alsoCheckBank(questBank));

		chisel = new ItemRequirement("Chisel", ItemID.CHISEL);
		chisel = chisel.showConditioned(chisel.alsoCheckBank(questBank));

		tinderbox = new ItemRequirement("Tinderbox", ItemID.TINDERBOX);
		tinderbox = tinderbox.showConditioned(tinderbox.alsoCheckBank(questBank));

		dynamite = new ItemRequirement("Dynamite", ItemID.DYNAMITE);
		dynamite = dynamite.showConditioned(dynamite.alsoCheckBank(questBank));

		lovakengj = new FavourRequirement(Favour.LOVAKENGJ, 100);

		shiloVillage = new QuestRequirement(QuestHelperQuest.SHILO_VILLAGE, 1, "Shilo Village completed");

		boneVoyage = new QuestRequirement(QuestHelperQuest.BONE_VOYAGE, 1, "Bone Voyage completed");
	}

	private void setupSteps()
	{

		tool = skillToolLvlChecker(skillLvl, tools);
		name = tool.getName();
		toolId = tool.getId();
		acquireNewPickaxe = new ObjectStep(this, toolId, "Go get a new " + name);
		mineCopperTinOre = new ObjectStep(this, ObjectID.ROCKS_11361, new WorldPoint(3226, 3146, 0),
			"Mine copper or tin ore until 15 Mining. You can choose to drop the ore, or bank them.",
			true,
			tool);//, prospectorBoots, prospectorHelmet, prospectorJacket, prospectorLegs);
		mineCopperTinOre.addAlternateObjects(ObjectID.ROCKS_11161);

		mineIronOre = new ObjectStep(this, ObjectID.ROCKS_11364, new WorldPoint(3295, 3310, 0),
			"Mine iron ore until 45/70 Mining. You can choose to drop the ore, or bank them.",
			true,
			tool);//prospectorBoots, prospectorHelmet, prospectorJacket, prospectorLegs);
		mineIronOre.addAlternateObjects(ObjectID.ROCKS_11365);

		mineMotherlode = new ObjectStep(this, ObjectID.ORE_VEIN_26661, new WorldPoint(3726, 5671, 0),
			"Mine at the motherlode. Collect pay-dirt and deposit into the hopper.", true,
			tool);//prospectorBoots, prospectorHelmet, prospectorJacket, prospectorLegs);
		mineMotherlode.addAlternateObjects(ObjectID.ORE_VEIN_26662, ObjectID.ORE_VEIN_26663, ObjectID.ORE_VEIN_26664);

		mineGem = new ObjectStep(this, ObjectID.ROCKS_11380, new WorldPoint(2821, 2999, 0),
			"Mine gem rocks at Shilo Village. Bring a charged Amulet of Glory for higher chance of receiving a gem. " +
				"Players who have completed the hard tasks of the Karamja Diary can access the underground portion of the mine.",
			true,
			tool, shiloVillage);//prospectorBoots, prospectorHelmet, prospectorJacket, prospectorLegs, amuletGlory, karamjaGloves3,
//			shiloVillage);
		mineGem.addAlternateObjects(ObjectID.ROCKS_11381);

		mineGranite = new ObjectStep(this, ObjectID.ROCKS_11387, new WorldPoint(3166, 2909, 0),
			"Mine Granite.",
			true,
			tool);//prospectorBoots, prospectorHelmet, prospectorJacket, prospectorLegs);

		mineVolcanic = new ObjectStep(this, ObjectID.VOLCANO_ENTRANCE, new WorldPoint(3816, 3808, 0),
			"Participate in the Volcanic Mine minigame.",
			true,
			tool, boneVoyage);//prospectorBoots, prospectorHelmet, prospectorJacket, prospectorLegs,
//			boneVoyage);
//		mineVolcanic.addAlternateObjects();

		mineBlast = new ObjectStep(this, ObjectID.HARD_ROCK, new WorldPoint(1488, 3871, 0),
			"Participate in the Blast Mine minigame. For effective strategies look into the wiki.",
			true,
			tool, chisel, tinderbox, dynamite,
			lovakengj);
//		,prospectorBoots, prospectorHelmet, prospectorJacket, prospectorLegs);
		mineBlast.addAlternateObjects(ObjectID.HARD_ROCK_28580);

		mineAmethyst = new ObjectStep(this, ObjectID.CRYSTALS, new WorldPoint(3024, 9704, 0),
			"Mine Amethyst Ore in the Mining guild.",
			true,
			tool, //prospectorBoots, prospectorHelmet, prospectorJacket, prospectorLegs,
			expertMiningGloves, varrockArmour4);
		mineAmethyst.addAlternateObjects(ObjectID.CRYSTALS_11389);


	}

	@Override
	public List<UnlockReward> getUnlockRewards()
	{
		return Collections.singletonList(
			new UnlockReward("Ability to purchase Mining Cape for 99k")
		);
	}

	@Override
	public List<ItemRequirement> getItemRequirements()
	{
		return Arrays.asList(ironPickAxe, steelPickAxe, blackPickAxe, mithrilPickAxe, adamantPickAxe, runePickAxe, dragonPickAxe);
	}

	@Override
	public List<ItemRequirement> getItemRecommended()
	{
		return Arrays.asList(prospectorHelmet, prospectorJacket, prospectorLegs, prospectorBoots);
	}

	@Override
	public List<PanelDetails> getPanels()
	{
		List<PanelDetails> allSteps = new ArrayList<>();
		allSteps.add(new PanelDetails("Acquire a new tool", Collections.singletonList(acquireNewPickaxe), tool));

		if (tool.check(client))
		{
			allSteps.get(0).setHideCondition(tool);
		}
		allSteps.add(new PanelDetails("1 - 15 Mine Copper or Tin ore", Collections.singletonList(mineCopperTinOre),
			tool,
//			ironPickAxe, steelPickAxe, blackPickAxe,
			prospectorBoots, prospectorHelmet, prospectorJacket, prospectorLegs));

		allSteps.add(new PanelDetails("15 - 45/70 Mine Iron ore", Collections.singletonList(mineIronOre),
			tool,
//			blackPickAxe, mithrilPickAxe, adamantPickAxe, runePickAxe, dragonPickAxe,
			new SkillRequirement(Skill.MINING, 15),
			prospectorBoots, prospectorHelmet, prospectorJacket, prospectorLegs));

		allSteps.add(new PanelDetails("30 - 99 Motherlode Mine", Collections.singletonList(mineMotherlode),
			tool,
//			mithrilPickAxe, adamantPickAxe, runePickAxe, dragonPickAxe,
			new SkillRequirement(Skill.MINING, 30),
			prospectorBoots, prospectorHelmet, prospectorJacket, prospectorLegs));

		allSteps.add(new PanelDetails("40 - 99 Mine Gem rocks.", Collections.singletonList(mineGem),
			tool,
//			adamantPickAxe, runePickAxe, dragonPickAxe,
			new SkillRequirement(Skill.MINING, 40),
			prospectorBoots, prospectorHelmet, prospectorJacket, prospectorLegs,
			amuletGlory, karamjaGloves3,
			shiloVillage));

		allSteps.add(new PanelDetails("45 - 99 Mine Granite", Collections.singletonList(mineGranite),
			tool,
//			runePickAxe, dragonPickAxe,
			new SkillRequirement(Skill.MINING, 45),
			prospectorBoots, prospectorHelmet, prospectorJacket, prospectorLegs));

		allSteps.add(new PanelDetails("70 - 99 Volcanic Mine", Collections.singletonList(mineVolcanic),
			tool,
//			runePickAxe, dragonPickAxe,
			new SkillRequirement(Skill.MINING, 70),
			prospectorBoots, prospectorHelmet, prospectorJacket, prospectorLegs));

		allSteps.add(new PanelDetails("75 - 99 Mine Blast Mine", Collections.singletonList(mineBlast),
			tool,
//			runePickAxe, dragonPickAxe,
			new SkillRequirement(Skill.MINING, 75),
			chisel, tinderbox, dynamite, lovakengj,
			prospectorBoots, prospectorHelmet, prospectorJacket, prospectorLegs));

		allSteps.add(new PanelDetails("92 - 99 Mine Amethyst", Collections.singletonList(mineAmethyst),
			tool,
//			runePickAxe, dragonPickAxe,
			new SkillRequirement(Skill.MINING, 92),
			prospectorBoots, prospectorHelmet, prospectorJacket, prospectorLegs,
			varrockArmour4, expertMiningGloves));

		return allSteps;
	}
}
