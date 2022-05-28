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
import com.questhelper.requirements.conditional.Conditions;
import com.questhelper.requirements.player.SkillRequirement;
import com.questhelper.requirements.util.LogicType;
import com.questhelper.rewards.UnlockReward;
import com.questhelper.steps.ConditionalStep;
import com.questhelper.steps.ObjectStep;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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

	// Items recommended
	ItemRequirement prospectorHelmet, prospectorJacket, prospectorLegs, prospectorBoots;

	SkillRequirement m6, m11, m21, m31, m41, m61;

	SkillRequirement m15, m30, m40, m45, m70, m75, m92;

	ObjectStep mineCopperTinOre, mineIronOre, mineMotherlode, mineGranite, mineGem, mineVolcanic, mineBlast, mineAmethyst;

	@Override
	public QuestStep loadStep()
	{
		setupRequirements();
		setupSteps();

		ConditionalStep fullTraining = new ConditionalStep(this, mineCopperTinOre);
//		fullTraining.addStep(m15, mineIronOre);
		fullTraining.addStep(m30, mineMotherlode);
//		fullTraining.addStep(m40, mineGem);
		fullTraining.addStep(m45, mineGranite);
//		fullTraining.addStep(m70, mineVolcanic);
//		fullTraining.addStep(m75, mineBlast);
//		fullTraining.addStep(m92, mineAmethyst);

		return fullTraining;
	}

	private void setupRequirements()
	{
		m6 = new SkillRequirement(Skill.MINING, 6);
		m11 = new SkillRequirement(Skill.MINING, 11);
		m21 = new SkillRequirement(Skill.MINING, 21);
		m31 = new SkillRequirement(Skill.MINING, 31);
		m41 = new SkillRequirement(Skill.MINING, 41);
		m61 = new SkillRequirement(Skill.MINING, 61);

		m15 = new SkillRequirement(Skill.MINING, 15);
		m30 = new SkillRequirement(Skill.MINING, 30);
//		m40 = new SkillRequirement(Skill.MINING, 40);
		m45 = new SkillRequirement(Skill.MINING, 45);
//		m70 = new SkillRequirement(Skill.MINING, 70);
//		m75 = new SkillRequirement(Skill.MINING, 75);
//		m92 = new SkillRequirement(Skill.MINING, 92);

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

		prospectorJacket = new ItemRequirement("Prospector Jacket", ItemID.PROSPECTOR_JACKET);
		prospectorJacket = prospectorJacket.showConditioned(prospectorJacket.alsoCheckBank(questBank));

		prospectorHelmet = new ItemRequirement("Prospector Helmet", ItemID.PROSPECTOR_HELMET);
		prospectorHelmet = prospectorHelmet.showConditioned(prospectorHelmet.alsoCheckBank(questBank));

		prospectorLegs = new ItemRequirement("Prospector legs", ItemID.PROSPECTOR_LEGS);
		prospectorLegs = prospectorLegs.showConditioned(prospectorLegs.alsoCheckBank(questBank));

		prospectorBoots = new ItemRequirement("Prospector boots", ItemID.PROSPECTOR_BOOTS);
		prospectorBoots = prospectorBoots.showConditioned(prospectorBoots.alsoCheckBank(questBank));

	}

	private void setupSteps()
	{
		mineCopperTinOre = new ObjectStep(this, ObjectID.ROCKS_11361, new WorldPoint(3226, 3146, 0),
			"Mine copper or tin ore until 15 Mining. You can choose to drop the ore, or " +
				"bank them.", true, ironPickAxe, steelPickAxe, blackPickAxe,
			prospectorBoots, prospectorHelmet, prospectorJacket, prospectorLegs);
		mineCopperTinOre.addAlternateObjects(ObjectID.ROCKS_11161);

		mineIronOre = new ObjectStep(this, ObjectID.ROCKS_11364, new WorldPoint(3295, 3310, 0),
			"Mine iron ore until 45/70 Mining. You can choose to drop the ore, or " +
				"bank them.", true, blackPickAxe, mithrilPickAxe, adamantPickAxe, runePickAxe, dragonPickAxe,
			prospectorBoots, prospectorHelmet, prospectorJacket, prospectorLegs);
		mineIronOre.addAlternateObjects(ObjectID.ROCKS_11365);

		mineMotherlode = new ObjectStep(this, ObjectID.ORE_VEIN_26661, new WorldPoint(3726, 5671, 0),
			"Mine at the motherlode. Collect pay-dirt and deposit into the hopper.", true, mithrilPickAxe, adamantPickAxe, runePickAxe, dragonPickAxe,
			prospectorBoots, prospectorHelmet, prospectorJacket, prospectorLegs);
		mineMotherlode.addAlternateObjects(ObjectID.ORE_VEIN_26662, ObjectID.ORE_VEIN_26663, ObjectID.ORE_VEIN_26664);

//		//edit object and worldpoint
//		mineGem = new ObjectStep(this, ObjectID, new WorldPoint(),
//			"", true, mithrilPickAxe, adamantPickAxe, runePickAxe, dragonPickAxe,
//			prospectorBoots, prospectorHelmet, prospectorJacket, prospectorLegs);
//		mineGem.addAlternateObjects();

		mineGranite = new ObjectStep(this, ObjectID.ROCKS_11387, new WorldPoint(3166, 2909, 0),
			"Mine Granite and drop the ore until 70 or all the way to 99. ", true,  runePickAxe, dragonPickAxe,
			prospectorBoots, prospectorHelmet, prospectorJacket, prospectorLegs);
		mineGranite.addAlternateObjects();

//		//edit object and worldpoint
//		mineVolcanic = new ObjectStep(this, ObjectID, new WorldPoint(),
//			"", true, mithrilPickAxe, adamantPickAxe, runePickAxe, dragonPickAxe,
//			prospectorBoots, prospectorHelmet, prospectorJacket, prospectorLegs);
//		mineVolcanic.addAlternateObjects();
//		//edit object and worldpoint
//		mineBlast = new ObjectStep(this, ObjectID, new WorldPoint(),
//			"", true, mithrilPickAxe, adamantPickAxe, runePickAxe, dragonPickAxe,
//			prospectorBoots, prospectorHelmet, prospectorJacket, prospectorLegs);
//		mineBlast.addAlternateObjects();
//		//edit object and worldpoint
//		mineAmethyst = new ObjectStep(this, ObjectID, new WorldPoint(),
//			"", true, mithrilPickAxe, adamantPickAxe, runePickAxe, dragonPickAxe,
//			prospectorBoots, prospectorHelmet, prospectorJacket, prospectorLegs);
//		mineAmethyst.addAlternateObjects();




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
		allSteps.add(new PanelDetails("1 - 15 Mine Copper or Tin ore", Collections.singletonList(mineCopperTinOre), ironPickAxe,
			steelPickAxe, blackPickAxe,
			prospectorBoots, prospectorHelmet, prospectorJacket, prospectorLegs));
		allSteps.add(new PanelDetails("15 - 30/99 Mine Iron ore", Collections.singletonList(mineIronOre), blackPickAxe,
			mithrilPickAxe, adamantPickAxe, runePickAxe, dragonPickAxe,
			prospectorBoots, prospectorHelmet, prospectorJacket, prospectorLegs));
		allSteps.add(new PanelDetails("30 - 45/99 Mine at the Motherlode Mine", Collections.singletonList(mineMotherlode), mithrilPickAxe,
			adamantPickAxe, runePickAxe, dragonPickAxe,
			prospectorBoots, prospectorHelmet, prospectorJacket, prospectorLegs));
		allSteps.add(new PanelDetails("45 - 70/99 Mine Granite ore", Collections.singletonList(mineGranite), runePickAxe,
			dragonPickAxe,
			prospectorBoots, prospectorHelmet, prospectorJacket, prospectorLegs));
		return allSteps;
	}
}
