/*
 * Copyright (c) 2021, Zoinkwiz
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
package com.questhelper.panel;

import com.google.inject.Inject;
import com.questhelper.Icon;
import com.questhelper.QuestHelperPlugin;
import com.questhelper.requirements.Requirement;
import com.questhelper.requirements.player.SkillRequirement;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Insets;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicButtonUI;
import lombok.Getter;
import lombok.Setter;
import net.runelite.api.Client;
import net.runelite.client.ui.ColorScheme;
import net.runelite.client.util.SwingUtil;

public class SkillRequirementPanel extends JPanel
{
	private static final ImageIcon INFO_ICON = Icon.INFO_ICON.getIcon();

	@Getter
	@Setter
	private JLabel label;

	@Inject
	Client client;

	@Getter
	private final Requirement requirement;

	public SkillRequirementPanel(Requirement requirement, QuestHelperPlugin questHelperPlugin, Client client)
	{
		this.requirement = requirement;
		this.client = client;

		setLayout(new BorderLayout());
		setBorder(new EmptyBorder(0, 0, 0, 0));

		String html1 = "<html><body style='padding: 0px; margin: 0px; width: 140px'>";
		String html2 = "</body></html>";

		StringBuilder text = new StringBuilder();

		if (requirement instanceof SkillRequirement)
		{
			try
			{
				text.append(requirement.getDisplayText());
			}
			catch (Exception e)
			{
				e.getCause();
			}

			SkillRequirement skillRequirement = ((SkillRequirement) requirement);
			label = new JLabel(html1 + text + html2);
			label.setForeground(Color.GRAY);
			label.setSize(label.getPreferredSize());
			setPreferredSize(label.getSize());

			add(label.add(addClickableSkillButton(skillRequirement, questHelperPlugin, client)), BorderLayout.WEST);
		}

		if (requirement.getTooltip() != null)
		{
			addButtonToPanel(requirement.getTooltip());
		}

	}

	private void addButtonToPanel(String tooltipText)
	{
		String html1 = "<html><body>";
		String html2 = "</body></html>";
		tooltipText = tooltipText.replaceAll("\\n", "<br>");
		JButton b = new JButton(INFO_ICON);
		b.setPreferredSize(new Dimension(10, 10));
		b.setToolTipText(html1 + tooltipText + html2);
		b.setBorderPainted(false);
		b.setFocusPainted(false);
		b.setBorderPainted(false);
		b.setContentAreaFilled(false);
		b.setMargin(new Insets(0, 0, 0, 0));
		add(b);
	}

	private Component addClickableSkillButton(SkillRequirement skillRequirement, QuestHelperPlugin questHelperPlugin, Client client)
	{
		JButton skillButton = new JButton();
		skillButton.setUI(new BasicButtonUI());
		SwingUtil.removeButtonDecorations(skillButton);
		skillButton.setHorizontalAlignment(SwingConstants.LEFT);
		skillButton.setBackground(ColorScheme.DARK_GRAY_COLOR);

		skillButton.setToolTipText("Open the skill guide for " + skillRequirement.getSkill().toString().toLowerCase());
		skillButton.setForeground(skillRequirement.getColor(client, questHelperPlugin.getConfig()));
		skillButton.setText("<html><body>" + skillRequirement.getDisplayText() + "</body></html>");

		skillButton.addActionListener((ev ->
		{
			skillButton.setForeground(Color.cyan.brighter().brighter().brighter());
			skillButton.setText("<html><body style = 'text-decoration:underline'>" + skillRequirement.getDisplayText() + "</body></html>");
			questHelperPlugin.onSkillReqSelected(skillRequirement);
		}
		));
		return skillButton;
	}
}

