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
package com.questhelper.quests.crafting;

import com.questhelper.QuestDescriptor;
import com.questhelper.QuestHelperQuest;
import com.questhelper.panel.PanelDetails;
import com.questhelper.questhelpers.ComplexStateQuestHelper;
import com.questhelper.requirements.conditional.Conditions;
import com.questhelper.requirements.item.ItemRequirement;
import com.questhelper.requirements.player.SkillRequirement;
import com.questhelper.requirements.player.SpellbookRequirement;
import com.questhelper.requirements.util.LogicType;
import com.questhelper.requirements.util.Spellbook;
import com.questhelper.rewards.UnlockReward;
import com.questhelper.steps.DetailedSkillStep;
import com.questhelper.steps.QuestStep;
import com.questhelper.steps.SkillStep;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import net.runelite.api.ItemID;
import net.runelite.api.Skill;
import net.runelite.client.plugins.skillcalculator.skills.CraftingAction;

@QuestDescriptor(
	quest = QuestHelperQuest.CRAFTING
)
public class Crafting extends ComplexStateQuestHelper
{
	ItemRequirement needle, thread, leather;
	SkillRequirement c7, c9, c11, c14;

	ItemRequirement chisel, uncutSapphire, uncutEmerald, uncutRuby, uncutDiamond, uncutDragonStone;
	SkillRequirement c20, c27, c34, c43, c55;

	ItemRequirement battleStaff, waterOrb, earthOrb, fireOrb, airOrb;
	SkillRequirement c54, c58, c62, c66;

	ItemRequirement greenDhide, blueDhide, redDhide, blackDhide;
	SkillRequirement c63, c71, c77, c84;

	ItemRequirement moltenGlass, glassBlowingPipe;
	SkillRequirement c1, c4, c12, c33, c42, c46, c49, c87;

	ItemRequirement braceletMould, goldBar, silverBar, opal, jade, topaz, sapphire, emerald, ruby, diamond, dragonStone;
	SkillRequirement c22, c23, c29, c30, c38, c74;

	ItemRequirement astralRunes, giantSeaweed, bucketsOfSand, smokeBattleStaff;
	SkillRequirement m77, c61;

	ItemRequirement amethyst;
	SkillRequirement c83, c85, c89, c99;
	SpellbookRequirement lunar;

	QuestStep craftLeatherGloves, craftLeatherBoots, craftLeatherCowl, craftLeatherVambs, craftLeatherBody;

	QuestStep cutSapphire, cutEmerald, cutRuby, cutDiamond, cutDragon;

	QuestStep craftWaterStaff, craftEarthStaff, craftFireStaff, craftAirStaff;

	QuestStep craftGreenHideBody, craftBlueHideBody, craftRedHideBody, craftBlackHideBody;

	QuestStep blowBeerGlass, blowCandleLantern, blowOilLamp, blowVial, blowFishbowl, blowOrb, blowLens, blowLightOrb;

	QuestStep goldBrace, opalBrace, sapphireBrace, jadeBrace, emeraldBrace, topazBrace, rubyBrace, diamondBrace, dragonBrace;

	QuestStep superGlassMake;

	QuestStep cutAmBolts, cutAmArrows, cutAmJavs, cutAmDarts;

	QuestStep acquireTools;

	private List<String> toolNames;

	@Override
	public QuestStep loadStep()
	{
		setupRequirements();
		setupSteps();

		SkillStep fullTraining = new SkillStep(this, acquireTools);

		fullTraining.addStep(c89, cutAmDarts);
		fullTraining.addStep(c87, cutAmJavs);
		fullTraining.addStep(c87, blowLightOrb);
		fullTraining.addStep(c85, cutAmArrows);
		fullTraining.addStep(c84, craftBlackHideBody);
		fullTraining.addStep(c83, cutAmBolts);
		fullTraining.addStep(c77, craftRedHideBody);
		fullTraining.addStep(c74, dragonBrace);
		fullTraining.addStep(c71, craftBlueHideBody);
		fullTraining.addStep(c66, craftAirStaff);
		fullTraining.addStep(c63, craftGreenHideBody);
		fullTraining.addStep(c62, craftFireStaff);
		fullTraining.addStep(c61, superGlassMake);
		fullTraining.addStep(c58, craftEarthStaff);
		fullTraining.addStep(c58, diamondBrace);
		fullTraining.addStep(c55, cutDragon);
		fullTraining.addStep(c54, craftWaterStaff);
		fullTraining.addStep(c49, blowLens);
		fullTraining.addStep(c46, blowOrb);
		fullTraining.addStep(c43, cutDiamond);
		fullTraining.addStep(c42, rubyBrace);
		fullTraining.addStep(c42, blowFishbowl);
		fullTraining.addStep(c38, topazBrace);
		fullTraining.addStep(c34, cutRuby);
		fullTraining.addStep(c33, blowVial);
		fullTraining.addStep(c30, emeraldBrace);
		fullTraining.addStep(c29, jadeBrace);
		fullTraining.addStep(c27, cutEmerald);
		fullTraining.addStep(c23, sapphireBrace);
		fullTraining.addStep(c22, opalBrace);
		fullTraining.addStep(c20, cutSapphire);
		fullTraining.addStep(c14, craftLeatherBody);
		fullTraining.addStep(c12, blowOilLamp);
		fullTraining.addStep(c11, craftLeatherVambs);
		fullTraining.addStep(c9, craftLeatherCowl);
		fullTraining.addStep(c7, craftLeatherBoots);
		fullTraining.addStep(c7, goldBrace);
		fullTraining.addStep(c4, blowCandleLantern);
		fullTraining.addStep(c1, craftLeatherGloves);
		fullTraining.addStep(c1, blowBeerGlass);

		return fullTraining;
	}

	public void setupRequirements()
	{
		c1 = new SkillRequirement(Skill.CRAFTING, 1);
		c4 = new SkillRequirement(Skill.CRAFTING, 4);
		c7 = new SkillRequirement(Skill.CRAFTING, 7);
		c9 = new SkillRequirement(Skill.CRAFTING, 9);
		c11 = new SkillRequirement(Skill.CRAFTING, 11);
		c12 = new SkillRequirement(Skill.CRAFTING, 12);
		c14 = new SkillRequirement(Skill.CRAFTING, 14);
		c20 = new SkillRequirement(Skill.CRAFTING, 20);
		c22 = new SkillRequirement(Skill.CRAFTING, 22);
		c23 = new SkillRequirement(Skill.CRAFTING, 23);
		c27 = new SkillRequirement(Skill.CRAFTING, 27);
		c29 = new SkillRequirement(Skill.CRAFTING, 29);
		c30 = new SkillRequirement(Skill.CRAFTING, 30);
		c33 = new SkillRequirement(Skill.CRAFTING, 33);
		c34 = new SkillRequirement(Skill.CRAFTING, 34);
		c38 = new SkillRequirement(Skill.CRAFTING, 38);
		c42 = new SkillRequirement(Skill.CRAFTING, 42);
		c43 = new SkillRequirement(Skill.CRAFTING, 43);
		c46 = new SkillRequirement(Skill.CRAFTING, 46);
		c49 = new SkillRequirement(Skill.CRAFTING, 49);
		c54 = new SkillRequirement(Skill.CRAFTING, 54);
		c55 = new SkillRequirement(Skill.CRAFTING, 55);
		c58 = new SkillRequirement(Skill.CRAFTING, 58);
		c61 = new SkillRequirement(Skill.CRAFTING, 61);
		c62 = new SkillRequirement(Skill.CRAFTING, 62);
		c63 = new SkillRequirement(Skill.CRAFTING, 63);
		c66 = new SkillRequirement(Skill.CRAFTING, 66);
		c71 = new SkillRequirement(Skill.CRAFTING, 71);
		c74 = new SkillRequirement(Skill.CRAFTING, 74);
		c77 = new SkillRequirement(Skill.CRAFTING, 77);
		c83 = new SkillRequirement(Skill.CRAFTING, 83);
		c84 = new SkillRequirement(Skill.CRAFTING, 84);
		c85 = new SkillRequirement(Skill.CRAFTING, 85);
		c87 = new SkillRequirement(Skill.CRAFTING, 87);
		c89 = new SkillRequirement(Skill.CRAFTING, 89);
		c99 = new SkillRequirement(Skill.CRAFTING, 99);
		m77 = new SkillRequirement(Skill.MAGIC, 77);

		lunar = new SpellbookRequirement(Spellbook.LUNAR);

		//Leather
		needle = new ItemRequirement("Needle", ItemID.NEEDLE).alsoCheckBank(questBank);

		thread = new ItemRequirement("Thread", ItemID.THREAD).alsoCheckBank(questBank);

		leather = new ItemRequirement("Leather", ItemID.LEATHER).showConditioned(
			new Conditions(c1, new Conditions(LogicType.NOR, c20))).alsoCheckBank(questBank);

		//Cutting Gems
		chisel = new ItemRequirement("Chisel", ItemID.CHISEL).alsoCheckBank(questBank);

		uncutSapphire = new ItemRequirement("Uncut Sapphire", ItemID.UNCUT_SAPPHIRE).showConditioned(
			new Conditions(c20, new Conditions(LogicType.NOR, c27))).alsoCheckBank(questBank);

		uncutEmerald = new ItemRequirement("Uncut Emerald", ItemID.UNCUT_EMERALD).showConditioned(
			new Conditions(c27, new Conditions(LogicType.NOR, c34))).alsoCheckBank(questBank);

		uncutRuby = new ItemRequirement("Uncut Ruby", ItemID.UNCUT_RUBY).showConditioned(
			new Conditions(c34, new Conditions(LogicType.NOR, c43))).alsoCheckBank(questBank);

		uncutDiamond = new ItemRequirement("Uncut Diamond", ItemID.UNCUT_DIAMOND).showConditioned(
			new Conditions(c43, new Conditions(LogicType.NOR, c55))).alsoCheckBank(questBank);

		uncutDragonStone = new ItemRequirement("Uncut Dragonstone", ItemID.UNCUT_DRAGONSTONE).showConditioned(
			new Conditions(c55, new Conditions(LogicType.NOR, c77))).alsoCheckBank(questBank);

		// BattleStaves
		battleStaff = new ItemRequirement("Battlestaff", ItemID.BATTLESTAFF).showConditioned(
			new Conditions(c54, new Conditions(LogicType.NOR, c77))).alsoCheckBank(questBank);

		waterOrb = new ItemRequirement("Water Orb", ItemID.WATER_ORB).showConditioned(
			new Conditions(c54, new Conditions(LogicType.NOR, c58))).alsoCheckBank(questBank);

		earthOrb = new ItemRequirement("Earth Orb", ItemID.EARTH_ORB).showConditioned(
			new Conditions(c58, new Conditions(LogicType.NOR, c62))).alsoCheckBank(questBank);

		fireOrb = new ItemRequirement("Fire Orb", ItemID.FIRE_ORB).showConditioned(
			new Conditions(c62, new Conditions(LogicType.NOR, c66))).alsoCheckBank(questBank);

		airOrb = new ItemRequirement("Air Orb", ItemID.AIR_ORB).showConditioned(
			new Conditions(c66, new Conditions(LogicType.NOR, c77))).alsoCheckBank(questBank);

		//DHide Bodies
		greenDhide = new ItemRequirement("Green dragonhide", ItemID.GREEN_DRAGONHIDE).showConditioned(
			new Conditions(c63, new Conditions(LogicType.NOR, c71))).alsoCheckBank(questBank);

		blueDhide = new ItemRequirement("Blue dragonhide", ItemID.BLUE_DRAGONHIDE).showConditioned(
			new Conditions(c71, new Conditions(LogicType.NOR, c77))).alsoCheckBank(questBank);

		redDhide = new ItemRequirement("Red dragonhide", ItemID.RED_DRAGONHIDE).showConditioned(
			new Conditions(c77, new Conditions(LogicType.NOR, c84))).alsoCheckBank(questBank);

		blackDhide = new ItemRequirement("Black dragonhide", ItemID.BLACK_DRAGONHIDE).showConditioned(
			new Conditions(c84)).alsoCheckBank(questBank);

		//Molten Glass
		moltenGlass = new ItemRequirement("Molten Glass", ItemID.MOLTEN_GLASS).alsoCheckBank(questBank);

		glassBlowingPipe = new ItemRequirement("Glassblowing Pipe", ItemID.GLASSBLOWING_PIPE).alsoCheckBank(questBank);

		//Crafting Bracelets
		braceletMould = new ItemRequirement("Bracelet Mould", ItemID.BRACELET_MOULD).showConditioned(
			new Conditions(c7)).alsoCheckBank(questBank);

		goldBar = new ItemRequirement("Gold Bar", ItemID.GOLD_BAR).showConditioned(
			new Conditions(c7)).alsoCheckBank(questBank);

		sapphire = new ItemRequirement("Sapphire", ItemID.SAPPHIRE).showConditioned(
			new Conditions(c23, new Conditions(LogicType.NOR, c30))).alsoCheckBank(questBank);

		emerald = new ItemRequirement("Emerald", ItemID.EMERALD).showConditioned(
			new Conditions(c30, new Conditions(LogicType.NOR, c42))).alsoCheckBank(questBank);

		ruby = new ItemRequirement("Ruby", ItemID.RUBY).showConditioned(
			new Conditions(c42, new Conditions(LogicType.NOR, c58))).alsoCheckBank(questBank);

		diamond = new ItemRequirement("Diamond", ItemID.DIAMOND).showConditioned(
			new Conditions(c58, new Conditions(LogicType.NOR, c74))).alsoCheckBank(questBank);

		dragonStone = new ItemRequirement("Dragonstone", ItemID.DRAGONSTONE).showConditioned(
			new Conditions(c74)).alsoCheckBank(questBank);

		silverBar = new ItemRequirement("Silver Bar", ItemID.SILVER_BAR).showConditioned(
			new Conditions(c22)).alsoCheckBank(questBank);

		opal = new ItemRequirement("Opal", ItemID.OPAL).showConditioned(
			new Conditions(c22, new Conditions(LogicType.NOR, c29))).alsoCheckBank(questBank);

		jade = new ItemRequirement("Jade", ItemID.JADE).showConditioned(
			new Conditions(c29, new Conditions(LogicType.NOR, c38))).alsoCheckBank(questBank);

		topaz = new ItemRequirement("Red Topaz", ItemID.RED_TOPAZ).showConditioned(
			new Conditions(c38, new Conditions(LogicType.NOR, c42))).alsoCheckBank(questBank);

		//Superglass make
		astralRunes = new ItemRequirement("Astral Runes", ItemID.ASTRAL_RUNE).showConditioned(
			new Conditions(c77)).alsoCheckBank(questBank);

		giantSeaweed = new ItemRequirement("Giant Seaweed", ItemID.GIANT_SEAWEED).showConditioned(
			new Conditions(c61)).alsoCheckBank(questBank);
		giantSeaweed.addAlternates(ItemID.SODA_ASH, ItemID.SEAWEED, ItemID.SWAMP_WEED);

		bucketsOfSand = new ItemRequirement("Buckets of Sand", ItemID.BUCKET_OF_SAND).showConditioned(
			new Conditions(c61)).alsoCheckBank(questBank);

		smokeBattleStaff = new ItemRequirement("Smoke Battlestaff", ItemID.SMOKE_BATTLESTAFF).showConditioned(
			new Conditions(m77)).alsoCheckBank(questBank);
		smokeBattleStaff.addAlternates(ItemID.MYSTIC_SMOKE_STAFF);

		//Amethyst
		amethyst = new ItemRequirement("Amethyst", ItemID.AMETHYST).showConditioned(
			new Conditions(c83)).alsoCheckBank(questBank);

		toolNames = Arrays.asList(chisel.getName(), needle.getName(), thread.getName(), braceletMould.getName());
	}

	private void setupSteps()
	{
		Skill skill = Skill.CRAFTING;

		craftLeatherGloves = new DetailedSkillStep(this, "1 - 7 Leather Gloves.",
			skill, 1, 7, CraftingAction.LEATHER_GLOVES.getXp(),
			needle, thread, leather, c1);

		craftLeatherBoots = new DetailedSkillStep(this, "7 - 9 Leather Boots.",
			skill, 7, 9, CraftingAction.LEATHER_BOOTS.getXp(),
			needle, thread, leather, c7);

		craftLeatherCowl = new DetailedSkillStep(this, "9 - 11 Leather Cowl.",
			skill, 9, 11, 18.5,
			needle, thread, leather, c9);

		craftLeatherVambs = new DetailedSkillStep(this, "11 - 14 Leather Vambraces.",
			skill, 11, 14, CraftingAction.LEATHER_VAMBRACES.getXp(),
			needle, thread, leather, c11);

		craftLeatherBody = new DetailedSkillStep(this, "14 - 20 Leather Body.",
			skill, 14, 20, CraftingAction.LEATHER_BODY.getXp(),
			needle, thread, leather, c14);

		//Cutting Gems
		cutSapphire = new DetailedSkillStep(this, "20 - 27 Cut Sapphire gems.",
			skill, 20, 27, CraftingAction.SAPPHIRE.getXp(),
			chisel, uncutSapphire, c20);

		cutEmerald = new DetailedSkillStep(this, "27 - 34 Cut Emerald gems.",
			skill, 27, 34, CraftingAction.EMERALD.getXp(),
			chisel, uncutEmerald, c27);

		cutRuby = new DetailedSkillStep(this, "34 - 43 Cut Ruby gems.",
			skill, 34, 43, CraftingAction.RUBY.getXp(),
			chisel, uncutRuby, c34);

		cutDiamond = new DetailedSkillStep(this, "43 - 55 Cut Diamond gems.",
			skill, 43, 55, CraftingAction.DIAMOND.getXp(),
			chisel, uncutDiamond, c43);

		cutDragon = new DetailedSkillStep(this, "55 - 77 Cut Dragonstone gems.",
			skill, 55, 62, CraftingAction.DIAMOND.getXp(),
			chisel, uncutDragonStone, c55);

		//Battlestaves
		craftWaterStaff = new DetailedSkillStep(this, "54 - 58 Water battlestaves.",
			skill, 43, 55, CraftingAction.WATER_BATTLESTAFF.getXp(),
			battleStaff, waterOrb, c43);

		craftEarthStaff = new DetailedSkillStep(this, "58 - 62 Earth battlestaves.",
			skill, 58, 62, CraftingAction.EARTH_BATTLESTAFF.getXp(),
			battleStaff, earthOrb, c58);

		craftFireStaff = new DetailedSkillStep(this, "61 - 66 Fire battlestaves.",
			skill, 62, 66, CraftingAction.FIRE_BATTLESTAFF.getXp(),
			battleStaff, fireOrb, c62);

		craftAirStaff = new DetailedSkillStep(this, "66 - 77 Air battlestaves.",
			skill, 66, 77, CraftingAction.FIRE_BATTLESTAFF.getXp(),
			battleStaff, airOrb, c66);

		//DHide Bodies
		craftGreenHideBody = new DetailedSkillStep(this, "63 - 71 Green d'hide bodies.",
			skill, 63, 71, CraftingAction.GREEN_DHIDE_BODY.getXp(),
			greenDhide, thread, needle, c63);

		craftBlueHideBody = new DetailedSkillStep(this, "71 - 77 Blue d'hide bodies.",
			skill, 71, 77, CraftingAction.BLUE_DHIDE_BODY.getXp(),
			blueDhide, thread, needle, c71);

		craftRedHideBody = new DetailedSkillStep(this, "77 - 84 Red d'hide bodies.",
			skill, 77, 84, CraftingAction.RED_DHIDE_BODY.getXp(),
			redDhide, thread, needle, c77);

		craftBlackHideBody = new DetailedSkillStep(this, "84 - 99 Black d'hide bodies.",
			skill, 84, 99, CraftingAction.BLACK_DHIDE_BODY.getXp(),
			blackDhide, thread, needle, c84);

		//Glassblowing
		blowBeerGlass = new DetailedSkillStep(this, "1 - 4 Beer glass.",
			skill, 1, 4, CraftingAction.BEER_GLASS.getXp(),
			glassBlowingPipe, moltenGlass, c1);

		blowCandleLantern = new DetailedSkillStep(this, "4 - 12 Empty candle lanterns.",
			skill, 4, 12, CraftingAction.EMPTY_CANDLE_LANTERN.getXp(),
			glassBlowingPipe, moltenGlass, c4);

		blowOilLamp = new DetailedSkillStep(this, "12 - 33 Empty oil lamp.",
			skill, 12, 33, CraftingAction.EMPTY_OIL_LAMP.getXp(),
			glassBlowingPipe, moltenGlass, c12);

		blowVial = new DetailedSkillStep(this, "33 - 42 Vials.",
			skill, 33, 42, CraftingAction.VIAL.getXp(),
			glassBlowingPipe, moltenGlass, c33);

		blowFishbowl = new DetailedSkillStep(this, "42 - 46 Empty fishbowls.",
			skill, 42, 46, CraftingAction.FISHBOWL.getXp(),
			glassBlowingPipe, moltenGlass, c42);

		blowOrb = new DetailedSkillStep(this, "46 - 49 Unpowered Orbs.",
			skill, 46, 49, CraftingAction.UNPOWERED_ORB.getXp(),
			glassBlowingPipe, moltenGlass, c46);

		blowLens = new DetailedSkillStep(this, "49 - 87 Lantern lens.",
			skill, 49, 87, CraftingAction.LANTERN_LENS.getXp(),
			glassBlowingPipe, moltenGlass, c49);

		blowLightOrb = new DetailedSkillStep(this, "87 - 99 Empty light orbs.",
			skill, 87, 99, CraftingAction.LIGHT_ORB.getXp(),
			glassBlowingPipe, moltenGlass, c87);

		//Gold Bracelets
		goldBrace = new DetailedSkillStep(this, "7 - 22 Gold bracelets.",
			skill, 7, 23, CraftingAction.GOLD_BRACELET.getXp(),
			braceletMould, goldBar, c7);

		sapphireBrace = new DetailedSkillStep(this, "23 - 30 Sapphire bracelets.",
			skill, 23, 30, CraftingAction.SAPPHIRE_BRACELET.getXp(),
			braceletMould, goldBar, sapphire, c23);

		emeraldBrace = new DetailedSkillStep(this, "30 - 42 Emerald bracelets.",
			skill, 30, 42, CraftingAction.EMERALD_BRACELET.getXp(),
			braceletMould, goldBar, emerald, c30);

		rubyBrace = new DetailedSkillStep(this, "42 - 58 Ruby bracelets.",
			skill, 42, 58, CraftingAction.RUBY_BRACELET.getXp(),
			braceletMould, goldBar, ruby, c42);

		diamondBrace = new DetailedSkillStep(this, "58 - 74 Diamond bracelets.",
			skill, 58, 74, CraftingAction.RUBY_BRACELET.getXp(),
			braceletMould, goldBar, diamond, c58);

		dragonBrace = new DetailedSkillStep(this, "74 - 99 Diamond bracelets.",
			skill, 74, 99, CraftingAction.DRAGONSTONE_BRACELET.getXp(),
			braceletMould, goldBar, dragonStone, c74);

		//Silver Bracelets
		opalBrace = new DetailedSkillStep(this, "22 - 29 Opal Bracelets.",
			skill, 22, 29, CraftingAction.OPAL_BRACELET.getXp(),
			braceletMould, silverBar, opal, c22);

		jadeBrace = new DetailedSkillStep(this, "29 - 38 Jade Bracelets.",
			skill, 29, 38, CraftingAction.JADE_BRACELET.getXp(),
			braceletMould, silverBar, jade, c29);

		topazBrace = new DetailedSkillStep(this, "38 - 42 Topaz Bracelets.",
			skill, 38, 42, CraftingAction.TOPAZ_BRACELET.getXp(),
			braceletMould, silverBar, topaz, c38);

		superGlassMake = new DetailedSkillStep(this, "61 - 99 Superglass Make.",
			giantSeaweed, bucketsOfSand, smokeBattleStaff, astralRunes, lunar, c61, m77);

		//Amethyst
		cutAmBolts = new DetailedSkillStep(this, "83 - 85 Amethyst bolt tips.",
			skill, 83, 85, CraftingAction.AMETHYST_BOLT_TIPS.getXp() * 15,
			chisel, amethyst, c83);

		cutAmArrows = new DetailedSkillStep(this, "85 - 87 Amethyst arrowtips.",
			skill, 85, 87, CraftingAction.AMETHYST_ARROWTIPS.getXp() * 15,
			chisel, amethyst, c85);

		cutAmJavs = new DetailedSkillStep(this, "87 - 89 Amethyst bolt tips.",
			skill, 87, 89, CraftingAction.AMETHYST_JAVELIN_HEADS.getXp() * 5,
			chisel, amethyst, c87);

		cutAmDarts = new DetailedSkillStep(this, "89 - 99 Amethyst dart tips.",
			skill, 89, 99, CraftingAction.AMETHYST_DART_TIP.getXp() * 8,
			chisel, amethyst, c89);

		acquireTools = new DetailedSkillStep(this, "Get a tool " + toolNames.toString());
	}

	@Override
	public List<UnlockReward> getUnlockRewards()
	{
		return Collections.singletonList(
			new UnlockReward("Ability to purchase Crafting Cape for 99k")
		);
	}

	@Override
	public List<ItemRequirement> getItemRequirements()
	{
		return Arrays.asList(needle, thread, leather, chisel, uncutSapphire, uncutEmerald, uncutRuby,
			uncutDiamond, uncutDragonStone, battleStaff, waterOrb, earthOrb, fireOrb, airOrb, greenDhide, blueDhide,
			redDhide, blackDhide, moltenGlass, glassBlowingPipe, braceletMould, goldBar, silverBar, opal, jade, topaz,
			sapphire, emerald, ruby, diamond, dragonStone, astralRunes, giantSeaweed, bucketsOfSand, amethyst);
	}

	@Override
	public List<PanelDetails> getPanels()
	{
		List<PanelDetails> allSteps = new ArrayList<>();

		allSteps.add(new PanelDetails("1 - 20: Leather Crafting", Arrays.asList(
			craftLeatherGloves, craftLeatherBoots, craftLeatherCowl, craftLeatherVambs, craftLeatherBody),
			needle, thread, leather));

		allSteps.add(new PanelDetails("20 - 62/77: Cutting Gems", Arrays.asList(
			cutSapphire, cutEmerald, cutRuby, cutDiamond, cutDragon),
			chisel, uncutSapphire, uncutEmerald, uncutRuby, uncutDiamond, uncutDragonStone));

		allSteps.add(new PanelDetails("54 - 77/99: Battlestaves", Arrays.asList(
			craftWaterStaff, craftEarthStaff, craftFireStaff, craftAirStaff),
			battleStaff, waterOrb, earthOrb, fireOrb, airOrb));

		allSteps.add(new PanelDetails("63 - 99: D'hide bodies", Arrays.asList(
			craftGreenHideBody, craftBlueHideBody, craftRedHideBody, craftBlackHideBody),
			needle, thread, greenDhide.quantity(3), blueDhide.quantity(3), redDhide.quantity(3),
			blackDhide.quantity(3)));

		allSteps.add(new PanelDetails("61 - 99: Superglass Make", Collections.singletonList(superGlassMake),
			m77, c61, lunar,
			smokeBattleStaff, astralRunes, bucketsOfSand, giantSeaweed));

		allSteps.add(new PanelDetails("83 - 99: Cutting Amethyst", Arrays.asList(
			cutAmBolts, cutAmArrows, cutAmJavs, cutAmDarts),
			chisel, amethyst));

		allSteps.add(new PanelDetails("1 - 83/99: Glassblowing", Arrays.asList(
			blowBeerGlass, blowCandleLantern, blowOilLamp, blowVial, blowFishbowl, blowOrb, blowLens, blowLightOrb),
			glassBlowingPipe, moltenGlass));

		allSteps.add(new PanelDetails("7 - 99: Bracelets", Arrays.asList(
			goldBrace, opalBrace, sapphireBrace, jadeBrace, emeraldBrace, topazBrace, rubyBrace, diamondBrace, dragonBrace),
			braceletMould, goldBar, silverBar, sapphire, jade, emerald, topaz, ruby, diamond, dragonStone));

		return allSteps;
	}
}
