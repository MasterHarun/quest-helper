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
package com.questhelper.quests.herblore;

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
import java.util.List;
import net.runelite.api.ItemID;
import com.questhelper.requirements.item.ItemRequirement;
import com.questhelper.panel.PanelDetails;
import com.questhelper.steps.QuestStep;
import com.questhelper.QuestDescriptor;
import net.runelite.api.Skill;
import net.runelite.client.plugins.skillcalculator.skills.HerbloreAction;

@QuestDescriptor(
	quest = QuestHelperQuest.HERBLORE
)
public class Herblore extends ComplexStateQuestHelper
{
	//Potion making
	QuestStep atkPot, antiPoison, strPot, serum207, compostPot, restorePot, energyPot, agilityPot, combatPot, prayerPot,
		superAtkPot, superAntiPoison, fishingPot, superEnergy, hunterPot, superStrPot, superRestore, superDefPot, antiFire, rangingPot,
		magicPot, staminaPot, bastionPot, battlemagePot, saradominBrew, extendedAntifire, ancientBrew, antiVenomPot, superCombatPot,
		superAntifirePot, antiVenumPlus, extendedSuperAntifire;

	ItemRequirement guamUnf, marrenUnf, tarrominUnf, harraUnf, toadUnf, ranarrUnf, iritUnf, avantoeUnf, kwuarmUnf,
		snapUnf, cadanUnf, lantaUnf, dwarfUnf, superEnergy4, cadaBloodUnf, antiFire4, antidotepp4, superAtk, superStr,
		superDef, antiVenom, superAntifire;

	ItemRequirement eyeNewt, unicornDust, limpRoot, ashes, volcAsh, redSpiderEggs, chocoDust, toadsLegs, goatDust,
		snapeGrass, mortFungus, kebbitDust, whiteBerries, dragonDust, wineOfZammy, potatoCactus, amylaseCrystal, crushedNest,
		lavaShard, nihilDust, zulrahScales, torstol, crushedSuperBones, pestle;

	SkillRequirement h3, h5, h12, h15, h22, h26, h34, h36, h38, h45, h48, h50, h52, h53, h55, h63, h66, h69, h72, h76, h77,
		h80, h81, h84, h85, h87, h90, h92, h94, h98, h99;

	//herb cleaning
	QuestStep getPestle, cleanGuam, cleanMarrentill, cleanTarromin, cleanHarralander, cleanRanarr, cleanToadflax, cleanIrit, cleanAvantoe,
		cleanKwuarm, cleanSnapdragon, cleanCadantine, cleanLantadyme, cleanDwarfweed, cleanTorstol;

	SkillRequirement h1, h11, h20, h25, h30, h40, h54, h59, h65, h67, h70, h75;

	ItemRequirement guam, marrentill, tarromin, harralander, ranarr, toadflax, irit, avantoe, kwuarm, snapdragon, cadantine,
		lantadyme, dwarfweed, grimyTorstol;


	@Override
	public QuestStep loadStep()
	{
		setupRequirements();
		setupSteps();

		/*
		 *	Currently just checks items.. Maybe introduce xp/hr as priority over items
		 *	This could prevent herb cleaning taking place over potion making if the required items are available.
		 *	even if the skill req is lower. faster xp is faster xp
		 */
		SkillStep fullTraining = new SkillStep(this, getPestle);
		fullTraining.addStep(h98, extendedSuperAntifire);
		fullTraining.addStep(h94, antiVenumPlus);
		fullTraining.addStep(h92, superAntifirePot);
		fullTraining.addStep(h90, superCombatPot);
		fullTraining.addStep(h87, antiVenomPot);
		fullTraining.addStep(h85, ancientBrew);
		fullTraining.addStep(h84, extendedAntifire);
		fullTraining.addStep(h81, saradominBrew);
		fullTraining.addStep(h80, bastionPot);
		fullTraining.addStep(h80, battlemagePot);
		fullTraining.addStep(h77, staminaPot);
		fullTraining.addStep(h76, magicPot);
		fullTraining.addStep(h75, cleanTorstol);
		fullTraining.addStep(h72, rangingPot);
		fullTraining.addStep(h70, cleanDwarfweed);
		fullTraining.addStep(h69, antiFire);
		fullTraining.addStep(h67, cleanCadantine);
		fullTraining.addStep(h66, superDefPot);
		fullTraining.addStep(h65, cleanCadantine);
		fullTraining.addStep(h63, superRestore);
		fullTraining.addStep(h59, cleanSnapdragon);
		fullTraining.addStep(h55, superStrPot);
		fullTraining.addStep(h54, cleanKwuarm);
		fullTraining.addStep(h53, hunterPot);
		fullTraining.addStep(h52, superEnergy);
		fullTraining.addStep(h50, fishingPot);
		fullTraining.addStep(h48, superAntiPoison);
		fullTraining.addStep(h45, superAtkPot);
		fullTraining.addStep(h40, cleanIrit);
		fullTraining.addStep(h38, prayerPot);
		fullTraining.addStep(h36, combatPot);
		fullTraining.addStep(h34, agilityPot);
		fullTraining.addStep(h30, cleanToadflax);
		fullTraining.addStep(h26, energyPot);
		fullTraining.addStep(h25, cleanRanarr);
		fullTraining.addStep(h22, restorePot);
		fullTraining.addStep(h22, compostPot);
		fullTraining.addStep(h20, cleanHarralander);
		fullTraining.addStep(h15, serum207);
		fullTraining.addStep(h12, strPot);
		fullTraining.addStep(h11, cleanTarromin);
		fullTraining.addStep(h5, antiPoison);
		fullTraining.addStep(h5, cleanMarrentill);
		fullTraining.addStep(h3, atkPot);
		fullTraining.addStep(h3, cleanGuam);

		return fullTraining;
	}

	public void setupRequirements()
	{
		Skill skill = Skill.HERBLORE;
		h1 = new SkillRequirement(skill, 1);
		h3 = new SkillRequirement(skill, 3);
		h5 = new SkillRequirement(skill, 5);
		h11 = new SkillRequirement(skill, 11);
		h12 = new SkillRequirement(skill, 12);
		h15 = new SkillRequirement(skill, 15);
		h20 = new SkillRequirement(skill, 20);
		h22 = new SkillRequirement(skill, 22);
		h25 = new SkillRequirement(skill, 25);
		h26 = new SkillRequirement(skill, 26);
		h30 = new SkillRequirement(skill, 30);
		h34 = new SkillRequirement(skill, 34);
		h36 = new SkillRequirement(skill, 36);
		h38 = new SkillRequirement(skill, 38);
		h40 = new SkillRequirement(skill, 40);
		h45 = new SkillRequirement(skill, 45);
		h48 = new SkillRequirement(skill, 48);
		h50 = new SkillRequirement(skill, 50);
		h52 = new SkillRequirement(skill, 52);
		h53 = new SkillRequirement(skill, 53);
		h54 = new SkillRequirement(skill, 54);
		h55 = new SkillRequirement(skill, 55);
		h59 = new SkillRequirement(skill, 59);
		h63 = new SkillRequirement(skill, 63);
		h65 = new SkillRequirement(skill, 65);
		h66 = new SkillRequirement(skill, 66);
		h67 = new SkillRequirement(skill, 67);
		h69 = new SkillRequirement(skill, 69);
		h70 = new SkillRequirement(skill, 70);
		h72 = new SkillRequirement(skill, 72);
		h75 = new SkillRequirement(skill, 75);
		h76 = new SkillRequirement(skill, 76);
		h77 = new SkillRequirement(skill, 77);
		h80 = new SkillRequirement(skill, 80);
		h81 = new SkillRequirement(skill, 81);
		h84 = new SkillRequirement(skill, 84);
		h85 = new SkillRequirement(skill, 85);
		h87 = new SkillRequirement(skill, 87);
		h90 = new SkillRequirement(skill, 90);
		h92 = new SkillRequirement(skill, 92);
		h94 = new SkillRequirement(skill, 94);
		h98 = new SkillRequirement(skill, 98);
		h99 = new SkillRequirement(skill, 99);

		pestle = new ItemRequirement("Pestle and Mortar", ItemID.PESTLE_AND_MORTAR);
		// Making potions
		guamUnf = new ItemRequirement("Guam potion (unf)", ItemID.GUAM_POTION_UNF).showConditioned(
			new Conditions(h3, new Conditions(LogicType.NOR, h5)));
		guamUnf = guamUnf.alsoCheckBank(questBank);

		eyeNewt = new ItemRequirement("Eye of newt", ItemID.EYE_OF_NEWT).showConditioned(
			new Conditions(LogicType.OR,
				new Conditions(h3, new Conditions(LogicType.NOR, h5)),
				new Conditions(h45, new Conditions(LogicType.NOR, h48))
				)
			);
		eyeNewt = eyeNewt.alsoCheckBank(questBank);

		marrenUnf = new ItemRequirement("Marrentill potion (unf)", ItemID.MARRENTILL_POTION_UNF).showConditioned(
			new Conditions(h5, new Conditions(LogicType.NOR, h12)));
		marrenUnf = marrenUnf.alsoCheckBank(questBank);

		unicornDust = new ItemRequirement("Unicorn horn dust", ItemID.UNICORN_HORN_DUST).showConditioned(
			new Conditions(h5, new Conditions(LogicType.NOR, h50)));
		unicornDust = unicornDust.alsoCheckBank(questBank);

		tarrominUnf = new ItemRequirement("Tarromin pot (Unf)", ItemID.TARROMIN_POTION_UNF).showConditioned(
			new Conditions(h12, new Conditions(LogicType.NOR, h22)));
		tarrominUnf = tarrominUnf.alsoCheckBank(questBank);

		limpRoot = new ItemRequirement("Limpwurt root", ItemID.LIMPWURT_ROOT).showConditioned(
			new Conditions(LogicType.OR,
				new Conditions(h12, new Conditions(LogicType.NOR, h15)),
				new Conditions(h55, new Conditions(LogicType.NOR, h63))
				)
			);
		limpRoot = limpRoot.alsoCheckBank(questBank);

		ashes = new ItemRequirement("Ashes", ItemID.ASHES).showConditioned(
			new Conditions(h15, new Conditions(LogicType.NOR, h22)));
		ashes = ashes.alsoCheckBank(questBank);

		harraUnf = new ItemRequirement("Harralander pot (unf)", ItemID.HARRALANDER_POTION_UNF).showConditioned(
			new Conditions(LogicType.OR,
				new Conditions(h22, new Conditions(LogicType.NOR, h34)),
				new Conditions(h36, new Conditions(LogicType.NOR, h38))
				)
			);
		harraUnf = harraUnf.alsoCheckBank(questBank);

		volcAsh = new ItemRequirement("Volcanic ash", ItemID.VOLCANIC_ASH).showConditioned(
			new Conditions(h22, new Conditions(LogicType.NOR, h26)));
		volcAsh = volcAsh.alsoCheckBank(questBank);

		redSpiderEggs = new ItemRequirement("Red spider's Eggs", ItemID.RED_SPIDERS_EGGS).showConditioned(
			new Conditions(LogicType.OR,
				new Conditions(h22, new Conditions(LogicType.NOR, h26)),
				new Conditions(h63, new Conditions(LogicType.NOR, h66))
				)
			);
		redSpiderEggs = redSpiderEggs.alsoCheckBank(questBank);

		chocoDust = new ItemRequirement("Chocolate dust", ItemID.CHOCOLATE_DUST).showConditioned(
			new Conditions(h26, new Conditions(LogicType.NOR, h34)));
		chocoDust = chocoDust.alsoCheckBank(questBank);

		toadUnf = new ItemRequirement("Toadflax pot(unf)", ItemID.TOADFLAX_POTION_UNF).showConditioned(
			new Conditions(LogicType.OR,
				new Conditions(h34, new Conditions(LogicType.NOR, h36)),
				new Conditions(h81, new Conditions(LogicType.NOR, h84))
			)
		);
		toadUnf = toadUnf.alsoCheckBank(questBank);

		toadsLegs = new ItemRequirement("Toad's legs", ItemID.TOADS_LEGS).showConditioned(
			new Conditions(h34, new Conditions(LogicType.NOR, h36)));
		toadsLegs = toadsLegs.alsoCheckBank(questBank);

		goatDust = new ItemRequirement("Goat horn dust", ItemID.GOAT_HORN_DUST).showConditioned(
			new Conditions(h36, new Conditions(LogicType.NOR, h38)));
		goatDust = goatDust.alsoCheckBank(questBank);

		ranarrUnf = new ItemRequirement("Ranarr pot (unf)", ItemID.RANARR_POTION_UNF).showConditioned(
			new Conditions(h38, new Conditions(LogicType.NOR, h45)));
		ranarrUnf = ranarrUnf.alsoCheckBank(questBank);

		snapeGrass = new ItemRequirement("Snape grass", ItemID.SNAPE_GRASS).showConditioned(
			new Conditions(LogicType.OR,
				new Conditions(h38, new Conditions(LogicType.NOR, h45)),
				new Conditions(h50, new Conditions(LogicType.NOR, h52))
				)
			);
		snapeGrass = snapeGrass.alsoCheckBank(questBank);

		iritUnf = new ItemRequirement("Irit pot (unf)", ItemID.IRIT_POTION_UNF).showConditioned(
			new Conditions(h45, new Conditions(LogicType.NOR, h50)));
		iritUnf = iritUnf.alsoCheckBank(questBank);

		avantoeUnf = new ItemRequirement("Avantoe pot (unf)", ItemID.AVANTOE_POTION_UNF).showConditioned(
			new Conditions(h50, new Conditions(LogicType.NOR, h55)));
		avantoeUnf = avantoeUnf.alsoCheckBank(questBank);

		mortFungus = new ItemRequirement("Mort myre fungus", ItemID.MORT_MYRE_FUNGUS).showConditioned(
			new Conditions(h52, new Conditions(LogicType.NOR, h53)));
		mortFungus = mortFungus.alsoCheckBank(questBank);

		kebbitDust = new ItemRequirement("Kebbit teeth dust", ItemID.KEBBIT_TEETH_DUST).showConditioned(
			new Conditions(h53, new Conditions(LogicType.NOR, h55)));
		kebbitDust = kebbitDust.alsoCheckBank(questBank);

		kwuarmUnf = new ItemRequirement("Kwuarm pot (unf)", ItemID.KWUARM_POTION_UNF).showConditioned(
			new Conditions(h55, new Conditions(LogicType.NOR, h63)));
		kwuarmUnf = kwuarmUnf.alsoCheckBank(questBank);

		snapUnf = new ItemRequirement("Snapdragon pot (unf)", ItemID.SNAPDRAGON_POTION_UNF).showConditioned(
			new Conditions(h63, new Conditions(LogicType.NOR, h66)));
		snapUnf = snapUnf.alsoCheckBank(questBank);

		cadanUnf = new ItemRequirement("Cadantine pot (unf)", ItemID.CADANTINE_POTION_UNF).showConditioned(
			new Conditions(h66, new Conditions(LogicType.NOR, h69)));
		cadanUnf = cadanUnf.alsoCheckBank(questBank);

		whiteBerries = new ItemRequirement("White berries", ItemID.WHITE_BERRIES).showConditioned(
			new Conditions(h66, new Conditions(LogicType.NOR, h69)));
		whiteBerries = whiteBerries.alsoCheckBank(questBank);

		lantaUnf = new ItemRequirement("Lantadyme pot (unf)", ItemID.LANTADYME_POTION_UNF).showConditioned(
			new Conditions(LogicType.OR,
				new Conditions(h69, new Conditions(LogicType.NOR, h72)),
				new Conditions(h76, new Conditions(LogicType.NOR, h77))
				)
			);
		lantaUnf = lantaUnf.alsoCheckBank(questBank);

		dragonDust = new ItemRequirement("Dragon scale dust", ItemID.DRAGON_SCALE_DUST).showConditioned(
			new Conditions(h69, new Conditions(LogicType.NOR, h72)));
		dragonDust = dragonDust.alsoCheckBank(questBank);

		dwarfUnf = new ItemRequirement("Dwarf weed pot (unf)", ItemID.DWARF_WEED_POTION_UNF).showConditioned(
			new Conditions(LogicType.OR,
				new Conditions(h72, new Conditions(LogicType.NOR, h76)),
				new Conditions(h85, new Conditions(LogicType.NOR, h87))
				)
			);
		dwarfUnf = dwarfUnf.alsoCheckBank(questBank);

		wineOfZammy = new ItemRequirement("Wine of Zamorak", ItemID.WINE_OF_ZAMORAK).showConditioned(
			new Conditions(LogicType.OR,
				new Conditions(h72, new Conditions(LogicType.NOR, h76)),
				new Conditions(h80, new Conditions(LogicType.NOR, h81))
				)
			);
		wineOfZammy = wineOfZammy.alsoCheckBank(questBank);

		potatoCactus = new ItemRequirement("Potato cactus", ItemID.POTATO_CACTUS).showConditioned(
			new Conditions(LogicType.OR,
				new Conditions(h76, new Conditions(LogicType.NOR, h77)),
				new Conditions(h80, new Conditions(LogicType.NOR, h81))
				)
			);
		potatoCactus = potatoCactus.alsoCheckBank(questBank);

		superEnergy4 = new ItemRequirement("Super energy (4)", ItemID.SUPER_ENERGY4).showConditioned(
			new Conditions(h77, new Conditions(LogicType.NOR, h80)));
		superEnergy4 = superEnergy4.alsoCheckBank(questBank);

		amylaseCrystal = new ItemRequirement("Amylase crystal (x4)", ItemID.AMYLASE_CRYSTAL).showConditioned(
			new Conditions(h77, new Conditions(LogicType.NOR, h80)));
		amylaseCrystal = amylaseCrystal.alsoCheckBank(questBank).quantity(4);

		cadaBloodUnf = new ItemRequirement("Cadantine blood pot (UNF)", ItemID.CADANTINE_BLOOD_POTION_UNF).showConditioned(
			new Conditions(h80, new Conditions(LogicType.NOR, h81)));
		cadaBloodUnf = cadaBloodUnf.alsoCheckBank(questBank);

		crushedNest = new ItemRequirement("Crushed nest", ItemID.CRUSHED_NEST).showConditioned(
			new Conditions(h81, new Conditions(LogicType.NOR, h84)));
		crushedNest = crushedNest.alsoCheckBank(questBank);

		antiFire4 = new ItemRequirement("Antifire pot (4)", ItemID.ANTIFIRE_POTION4).showConditioned(
			new Conditions(h84, new Conditions(LogicType.NOR, h85)));
		antiFire4 = antiFire4.alsoCheckBank(questBank);

		lavaShard = new ItemRequirement("Lava scale shard (x4)", ItemID.LAVA_SCALE_SHARD).showConditioned(
			new Conditions(LogicType.OR,
				new Conditions(h84, new Conditions(LogicType.NOR, h85)),
				new Conditions(h98, new Conditions(LogicType.NOR, h99))
				)
			);
		lavaShard = lavaShard.alsoCheckBank(questBank).quantity(4);

		nihilDust = new ItemRequirement("Nihil dust", ItemID.NIHIL_DUST).showConditioned(
			new Conditions(h85, new Conditions(LogicType.NOR, h87)));
		nihilDust = nihilDust.alsoCheckBank(questBank);

		antidotepp4 = new ItemRequirement("Antidote++ (4)", ItemID.ANTIDOTE4).showConditioned(
			new Conditions(h87, new Conditions(LogicType.NOR, h90)));
		antidotepp4 = antidotepp4.alsoCheckBank(questBank);

		zulrahScales = new ItemRequirement("Zulrah's scales (x20)", ItemID.ZULRAHS_SCALES).showConditioned(
			new Conditions(h87, new Conditions(LogicType.NOR, h90)));
		zulrahScales = zulrahScales.alsoCheckBank(questBank).quantity(20);

		superAtk = new ItemRequirement("Super attack (4)", ItemID.SUPER_ATTACK4).showConditioned(
			new Conditions(h90, new Conditions(LogicType.NOR, h92)));
		superAtk = superAtk.alsoCheckBank(questBank);

		superDef = new ItemRequirement("Super defence (4)", ItemID.SUPER_DEFENCE4).showConditioned(
			new Conditions(h90, new Conditions(LogicType.NOR, h92)));
		superDef = superDef.alsoCheckBank(questBank);

		superStr = new ItemRequirement("Super strength (4)", ItemID.SUPER_STRENGTH4).showConditioned(
			new Conditions(h90, new Conditions(LogicType.NOR, h92)));
		superStr = superStr.alsoCheckBank(questBank);

		torstol = new ItemRequirement("Torstol", ItemID.TORSTOL).showConditioned(
			new Conditions(LogicType.OR,
				new Conditions(h90, new Conditions(LogicType.NOR, h92)),
				new Conditions(h94, new Conditions(LogicType.NOR, h98))
				)
			);
		torstol = torstol.alsoCheckBank(questBank);

		antiFire4 = new ItemRequirement("Antifire pot (4)", ItemID.ANTIFIRE_POTION4).showConditioned(
			new Conditions(h92, new Conditions(LogicType.NOR, h94)));
		antiFire4 = antiFire4.alsoCheckBank(questBank);

		crushedSuperBones = new ItemRequirement("Crushed superior dragon bones", ItemID.CRUSHED_SUPERIOR_DRAGON_BONES).showConditioned(
			new Conditions(h92, new Conditions(LogicType.NOR, h94)));
		crushedSuperBones = crushedSuperBones.alsoCheckBank(questBank);

		antiVenom = new ItemRequirement("Antivenom (4)", ItemID.ANTIVENOM4).showConditioned(
			new Conditions(h94, new Conditions(LogicType.NOR, h98)));
		antiVenom = antiVenom.alsoCheckBank(questBank);

		superAntifire = new ItemRequirement("Super antifire pot (4)", ItemID.SUPER_ANTIFIRE_POTION4).showConditioned(
			new Conditions(h98));
		superAntifire = superAntifire.alsoCheckBank(questBank);


		// Cleaning Herbs
		guam = new ItemRequirement("Grimy Guam leaf", ItemID.GRIMY_GUAM_LEAF).showConditioned(
			new Conditions(h3, new Conditions(LogicType.NOR, h5)));
		guam = guam.alsoCheckBank(questBank);

		marrentill = new ItemRequirement("Grimy Marrentill", ItemID.GRIMY_MARRENTILL).showConditioned(
			new Conditions(h5, new Conditions(LogicType.NOR, h11)));
		marrentill = marrentill.alsoCheckBank(questBank);

		tarromin = new ItemRequirement("Grimy Tarromin", ItemID.GRIMY_TARROMIN).showConditioned(
			new Conditions(h11, new Conditions(LogicType.NOR, h20)));
		tarromin = tarromin.alsoCheckBank(questBank);

		harralander = new ItemRequirement("Grimy Harralander", ItemID.GRIMY_HARRALANDER).showConditioned(
			new Conditions(h20, new Conditions(LogicType.NOR, h25)));
		harralander = harralander.alsoCheckBank(questBank);

		ranarr = new ItemRequirement("Grimy Ranarr weed", ItemID.GRIMY_RANARR_WEED).showConditioned(
			new Conditions(h25, new Conditions(LogicType.NOR, h30)));
		ranarr = ranarr.alsoCheckBank(questBank);

		toadflax = new ItemRequirement("Grimy Toadflax", ItemID.GRIMY_TOADFLAX).showConditioned(
			new Conditions(h30, new Conditions(LogicType.NOR, h40)));
		toadflax = toadflax.alsoCheckBank(questBank);

		irit = new ItemRequirement("Grimy Irit leaf", ItemID.GRIMY_IRIT_LEAF).showConditioned(
			new Conditions(h40, new Conditions(LogicType.NOR, h48)));
		irit = irit.alsoCheckBank(questBank);

		avantoe = new ItemRequirement("Grimy Avantoe", ItemID.GRIMY_AVANTOE).showConditioned(
			new Conditions(h48, new Conditions(LogicType.NOR, h54)));
		avantoe = avantoe.alsoCheckBank(questBank);

		kwuarm = new ItemRequirement("Grimy Kwuarm", ItemID.GRIMY_KWUARM).showConditioned(
			new Conditions(h54, new Conditions(LogicType.NOR, h59)));
		kwuarm = kwuarm.alsoCheckBank(questBank);

		snapdragon = new ItemRequirement("Grimy Snapdragon", ItemID.GRIMY_SNAPDRAGON).showConditioned(
			new Conditions(h59, new Conditions(LogicType.NOR, h65)));
		snapdragon = snapdragon.alsoCheckBank(questBank);

		cadantine = new ItemRequirement("Grimy Cadantine", ItemID.GRIMY_CADANTINE).showConditioned(
			new Conditions(h65, new Conditions(LogicType.NOR, h67)));
		cadantine = cadantine.alsoCheckBank(questBank);

		lantadyme = new ItemRequirement("Grimy Lantadyme", ItemID.GRIMY_LANTADYME).showConditioned(
			new Conditions(h67, new Conditions(LogicType.NOR, h70)));
		lantadyme = lantadyme.alsoCheckBank(questBank);

		dwarfweed = new ItemRequirement("Grimy Dwarf weed", ItemID.GRIMY_DWARF_WEED).showConditioned(
			new Conditions(h70, new Conditions(LogicType.NOR, h75)));
		dwarfweed = dwarfweed.alsoCheckBank(questBank);

		grimyTorstol = new ItemRequirement("Grimy Torstol", ItemID.GRIMY_TORSTOL).showConditioned(
			new Conditions(h75, new Conditions(LogicType.NOR, h99)));
		torstol = torstol.alsoCheckBank(questBank);

	}

	private void setupSteps()
	{
		Skill skill = Skill.HERBLORE;

		getPestle = new ObjectStep(this, pestle.getId(), "Get Pestle and Mortar", h1);
		atkPot = new DetailedSkillStep(this, "3 - 5 Attack Potion", skill, 3, 5,
			HerbloreAction.ATTACK_POTION_3.getXp(),
			guamUnf, eyeNewt, h3);

		antiPoison = new DetailedSkillStep(this, "5 - 12 Antipoison", skill, 5, 12,
			HerbloreAction.ANTIPOISON_3.getXp(),
			marrenUnf, unicornDust, h5);

		strPot = new DetailedSkillStep(this, "12 - 15 Strength Potion", skill, 12, 15,
			HerbloreAction.STRENGTH_POTION_3.getXp(),
			tarrominUnf, limpRoot, h12);

		serum207 = new DetailedSkillStep(this, "15 - 22 Serum 207", skill, 15, 22,
			HerbloreAction.SERUM_207_3.getXp(),
			tarrominUnf, ashes, h15);

		compostPot = new DetailedSkillStep(this, "22 - 26 Compost Potion", skill, 22, 26,
			HerbloreAction.COMPOST_POTION_3.getXp(),
			harraUnf, volcAsh, h22);

		restorePot = new DetailedSkillStep(this, "22 - 26 Restore Potion", skill, 22, 26,
			HerbloreAction.RESTORE_POTION_3.getXp(),
			harraUnf, redSpiderEggs, h22);

		energyPot = new DetailedSkillStep(this, "26 - 34 Energy Potion", skill, 26, 34,
			HerbloreAction.ENERGY_POTION_3.getXp(),
			harraUnf, chocoDust, h26);

		agilityPot = new DetailedSkillStep(this, "34 - 36 Agility Potion", skill, 34, 36,
			HerbloreAction.AGILITY_POTION_3.getXp(),
			toadUnf, toadsLegs, h34);

		combatPot = new DetailedSkillStep(this, "36 - 38 Combat Potion", skill, 36, 38,
			HerbloreAction.COMBAT_POTION_3.getXp(),
			harraUnf, goatDust, h36);

		prayerPot = new DetailedSkillStep(this, "38 - 45 Prayer Potion", skill, 38, 45,
			HerbloreAction.PRAYER_POTION_3.getXp(),
			ranarrUnf, snapeGrass, h38);

		superAtkPot = new DetailedSkillStep(this, "45 - 48 Super Attack Potion", skill, 45, 48,
			HerbloreAction.SUPER_ATTACK_3.getXp(),
			iritUnf, eyeNewt, h45);

		superAntiPoison = new DetailedSkillStep(this, "48 - 50 Super Antipoison", skill, 48, 50,
			HerbloreAction.SUPERANTIPOISON_3.getXp(),
			iritUnf, unicornDust, h48);

		fishingPot = new DetailedSkillStep(this, "50 - 52 Fishing Potion", skill, 50, 52,
			HerbloreAction.FISHING_POTION_3.getXp(),
			avantoeUnf, snapeGrass, h50);

		superEnergy = new DetailedSkillStep(this, "52 - 53 Super Energy Potion", skill, 52, 53,
			HerbloreAction.SUPER_ENERGY_3.getXp(),
			avantoeUnf, mortFungus, h52);

		hunterPot = new DetailedSkillStep(this, "53 - 55 Hunter Potion", skill, 53, 55,
			HerbloreAction.HUNTER_POTION_3.getXp(),
			avantoeUnf, kebbitDust, h53);

		superStrPot = new DetailedSkillStep(this, "55 - 63 Super Strength Potion", skill, 55, 63,
			HerbloreAction.SUPER_STRENGTH_3.getXp(),
			kwuarmUnf, limpRoot, h55);

		superRestore = new DetailedSkillStep(this, "63 - 66 Super Restore Potion", skill, 63, 66,
			HerbloreAction.SUPER_RESTORE_3.getXp(),
			snapUnf, redSpiderEggs, h63);

		superDefPot = new DetailedSkillStep(this, "66 - 69 Super Defence Potion", skill, 66, 69,
			HerbloreAction.SUPER_DEFENCE_3.getXp(),
			cadanUnf, whiteBerries, h66);

		antiFire = new DetailedSkillStep(this, "69 - 72 Antifire Potion", skill, 69, 72,
			HerbloreAction.ANTIFIRE_POTION_3.getXp(),
			lantaUnf, dragonDust, h69);

		rangingPot = new DetailedSkillStep(this, "72 - 76 Ranging Potion", skill, 72, 76,
			HerbloreAction.RANGING_POTION_3.getXp(),
			dwarfUnf, wineOfZammy, h72);

		magicPot = new DetailedSkillStep(this, "76 - 77 Magic Potion", skill, 76, 77,
			HerbloreAction.MAGIC_POTION_3.getXp(),
			lantaUnf, potatoCactus, h76);

		staminaPot = new DetailedSkillStep(this, "77 - 80 Stamina Potion", skill, 77, 80,
			HerbloreAction.STAMINA_POTION_3.getXp(),
			superEnergy4, amylaseCrystal, h77);

		bastionPot = new DetailedSkillStep(this, "80 - 81 Bastion Potion", skill, 80, 81,
			HerbloreAction.BASTION_POTION_3.getXp(),
			cadaBloodUnf, wineOfZammy, h80);

		battlemagePot = new DetailedSkillStep(this, "80 - 81 Battlemage Potion", skill, 80, 81,
			HerbloreAction.BATTLEMAGE_POTION_3.getXp(),
			cadaBloodUnf, potatoCactus, h80);

		saradominBrew = new DetailedSkillStep(this, "81 - 84 Saradoming Brew", skill, 81, 84,
			HerbloreAction.SARADOMIN_BREW_3.getXp(),
			toadUnf, crushedNest, h81);

		extendedAntifire = new DetailedSkillStep(this, "84 - 85 Extended antifire Potion", skill, 84, 85,
			HerbloreAction.EXTENDED_ANTIFIRE_3.getXp(),
			antiFire4, lavaShard, h84);

		ancientBrew = new DetailedSkillStep(this, "85 - 87 Ancient Brew", skill, 85, 87,
			HerbloreAction.ANCIENT_BREW_4.getXp(),
			dwarfUnf, nihilDust, h85);

		antiVenomPot = new DetailedSkillStep(this, "87 - 90 Anti-venom", skill, 87, 90,
			HerbloreAction.ANTIVENOM_3.getXp(),
			antidotepp4, zulrahScales, h87);

		superCombatPot = new DetailedSkillStep(this, "90 - 92 Super Combat", skill, 90, 92,
			HerbloreAction.SUPER_COMBAT_POTION_4.getXp(),
			superAtk, superDef, superStr, h90);

		superAntifirePot = new DetailedSkillStep(this, "92 - 94 Super antifire", skill, 92, 94,
			HerbloreAction.SUPER_ANTIFIRE_4.getXp(),
			antiFire4, crushedSuperBones, h92);

		antiVenumPlus = new DetailedSkillStep(this, "94 - 98 Anti-venom++", skill, 94, 98,
			HerbloreAction.ANTIVENOM_PLUS_4.getXp(),
			antiVenom, torstol, h94);

		extendedSuperAntifire = new DetailedSkillStep(this, "98 - 99 Extended Super antifire", skill, 98, 99,
			HerbloreAction.EXTENDED_SUPER_ANTIFIRE_3.getXp(),
			superAntifire, lavaShard, h98);

		// Cleaning Herbs
		cleanGuam = new DetailedSkillStep(this, "3 - 5 Grimy Guam", skill, 3, 5,
			HerbloreAction.GUAM_LEAF.getXp(),
			guam, h3);

		cleanMarrentill = new DetailedSkillStep(this, "5 - 11 Grimy Marrentill", skill, 5, 11,
			HerbloreAction.MARRENTILL.getXp(),
			marrentill, h5);

		cleanTarromin = new DetailedSkillStep(this, "11 - 20 Grimy Tarromin", skill, 11, 20,
			HerbloreAction.TARROMIN.getXp(),
			tarromin, h11);

		cleanHarralander = new DetailedSkillStep(this, "20 - 25 Grimy Harralander", skill, 20, 25,
			HerbloreAction.HARRALANDER.getXp(),
			harralander, h20);

		cleanRanarr = new DetailedSkillStep(this, "25 - 30 Grimy Ranarr Weed", skill, 25, 30,
			HerbloreAction.RANARR_WEED.getXp(),
			ranarr, h25);

		cleanToadflax = new DetailedSkillStep(this, "30 - 40 Grimy Toadflax", skill, 30, 40,
			HerbloreAction.TOADFLAX.getXp(),
			toadflax, h30);

		cleanIrit = new DetailedSkillStep(this, "40 - 48 Grimy Irit", skill, 40, 48,
			HerbloreAction.IRIT_LEAF.getXp(),
			irit, h40);

		cleanAvantoe = new DetailedSkillStep(this, "48 - 54 Grimy Avantoe", skill, 48, 54,
			HerbloreAction.AVANTOE.getXp(),
			avantoe, h48);

		cleanKwuarm = new DetailedSkillStep(this, "54 - 59 Grimy Kwuarm", skill, 54, 59,
			HerbloreAction.KWUARM.getXp(),
			kwuarm, h54);

		cleanSnapdragon = new DetailedSkillStep(this, "59 - 65 Grimy Snapdragon", skill, 59, 65,
			HerbloreAction.SNAPDRAGON.getXp(),
			snapdragon, h59);

		cleanCadantine = new DetailedSkillStep(this, "65 - 67 Grimy Cadantine", skill, 65, 67,
			HerbloreAction.CADANTINE.getXp(),
			cadantine, h65);

		cleanLantadyme = new DetailedSkillStep(this, "67 - 70 Grimy Lantadyme", skill, 67, 70,
			HerbloreAction.LANTADYME.getXp(),
			lantadyme, h67);

		cleanDwarfweed = new DetailedSkillStep(this, "70 - 75 Grimy Dwarf Weed", skill, 70, 75,
			HerbloreAction.DWARF_WEED.getXp(),
			dwarfweed, h70);

		cleanTorstol = new DetailedSkillStep(this, "75 - 99 Grimy Torstol", skill, 75, 99,
			HerbloreAction.TORSTOL.getXp(),
			grimyTorstol, h75);

	}

	@Override
	public List<UnlockReward> getUnlockRewards()
	{
		return Collections.singletonList(
			new UnlockReward("Ability to purchase Herblore Cape for 99k")
		);
	}

	@Override
	public List<ItemRequirement> getItemRequirements()
	{
		return Arrays.asList(guamUnf, marrenUnf, tarrominUnf, harraUnf, toadUnf, ranarrUnf, iritUnf, avantoeUnf, kwuarmUnf,
			snapUnf, cadanUnf, lantaUnf, dwarfUnf, superEnergy4, cadaBloodUnf, antiFire4, antidotepp4, superAtk, superStr,
			superDef, antiVenom, superAntifire, eyeNewt, unicornDust, limpRoot, ashes, volcAsh, redSpiderEggs, chocoDust, toadsLegs, goatDust,
			snapeGrass, mortFungus, kebbitDust, whiteBerries, dragonDust, wineOfZammy, potatoCactus, amylaseCrystal, crushedNest,
			lavaShard, nihilDust, zulrahScales, torstol, crushedSuperBones, guam, marrentill, tarromin, harralander, ranarr, toadflax, irit, avantoe, kwuarm, snapdragon, cadantine,
			lantadyme, dwarfweed, grimyTorstol);
	}

	@Override
	public List<PanelDetails> getPanels()
	{
		List<PanelDetails> allSteps = new ArrayList<>();

		allSteps.add(new PanelDetails("Potion Making", Arrays.asList(atkPot, antiPoison, strPot, serum207, compostPot, restorePot, energyPot, agilityPot, combatPot, prayerPot,
			superAtkPot, superAntiPoison, fishingPot, superEnergy, hunterPot, superStrPot, superRestore, superDefPot, antiFire, rangingPot,
			magicPot, staminaPot, bastionPot, battlemagePot, saradominBrew, extendedAntifire, ancientBrew, antiVenomPot, superCombatPot,
			superAntifirePot, antiVenumPlus, extendedSuperAntifire),
			//items
			guamUnf, marrenUnf, tarrominUnf, harraUnf, toadUnf, ranarrUnf, iritUnf, avantoeUnf, kwuarmUnf,
			snapUnf, cadanUnf, lantaUnf, dwarfUnf, superEnergy4, cadaBloodUnf, antiFire4, antidotepp4, superAtk, superStr,
			superDef, antiVenom, superAntifire, eyeNewt, unicornDust, limpRoot, ashes, volcAsh, redSpiderEggs, chocoDust, toadsLegs, goatDust,
			snapeGrass, mortFungus, kebbitDust, whiteBerries, dragonDust, wineOfZammy, potatoCactus, amylaseCrystal, crushedNest,
			lavaShard, nihilDust, zulrahScales, torstol, crushedSuperBones));

		allSteps.add(new PanelDetails("Cleaning Herbs", Arrays.asList(cleanGuam, cleanMarrentill, cleanTarromin, cleanHarralander, cleanRanarr, cleanToadflax, cleanIrit, cleanAvantoe,
			cleanKwuarm, cleanSnapdragon, cleanCadantine, cleanLantadyme, cleanDwarfweed, cleanTorstol),
			//items
			guam, marrentill, tarromin, harralander, ranarr, toadflax, irit, avantoe, kwuarm, snapdragon, cadantine,
			lantadyme, dwarfweed, grimyTorstol));

		return allSteps;
	}
}
