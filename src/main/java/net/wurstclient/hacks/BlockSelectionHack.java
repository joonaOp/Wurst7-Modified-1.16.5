/*
 * Copyright (c) 2014-2023 Wurst-Imperium and contributors.
 *
 * This source code is subject to the terms of the GNU General Public
 * License, version 3. If a copy of the GPL was not distributed with this
 * file, You can obtain one at: https://www.gnu.org/licenses/gpl-3.0.txt
 */
package net.wurstclient.hacks;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.registry.Registry;
import net.wurstclient.Category;
import net.wurstclient.hack.Hack;
import net.wurstclient.settings.CheckboxSetting;
import net.wurstclient.settings.SliderSetting;
import net.wurstclient.settings.SliderSetting.ValueDisplay;
import net.wurstclient.settings.TextSetting;

public final class BlockSelectionHack extends Hack
{
	private final TextSetting searchQuery = new TextSetting("Search",
		"Search for blocks by name", "");
	
	private final CheckboxSetting includeStoneVariants =
		new CheckboxSetting("Include Stone Variants",
			"Include different stone types and variants", true);
	
	private final CheckboxSetting includeDirtVariants =
		new CheckboxSetting("Include Dirt Variants",
			"Include different dirt types and variants", true);
	
	private final CheckboxSetting includeWoodVariants =
		new CheckboxSetting("Include Wood Variants",
			"Include different wood types and variants", true);
	
	private final CheckboxSetting includeOreBlocks =
		new CheckboxSetting("Include Ore Blocks",
			"Include all ore blocks", true);
	
	private final SliderSetting maxResults = new SliderSetting(
		"Max Results", "Maximum search results to display", 50, 10, 500, 10,
		ValueDisplay.INTEGER);
	
	private List<Block> selectedBlocks = new ArrayList<>();
	private List<Block> filteredBlocks = new ArrayList<>();
	
	public BlockSelectionHack()
	{
		super("BlockSelection");
		setCategory(Category.OTHER);
		addSetting(searchQuery);
		addSetting(includeStoneVariants);
		addSetting(includeDirtVariants);
		addSetting(includeWoodVariants);
		addSetting(includeOreBlocks);
		addSetting(maxResults);
	}
	
	@Override
	public void onEnable()
	{
		initializeBlockList();
		updateFilteredBlocks();
	}
	
	@Override
	public void onDisable()
	{
		selectedBlocks.clear();
		filteredBlocks.clear();
	}
	
	private void initializeBlockList()
	{
		selectedBlocks.clear();
		selectedBlocks.addAll(Registry.BLOCK);
	}
	
	public void updateFilteredBlocks()
	{
		String query = searchQuery.getValue().toLowerCase();
		filteredBlocks = selectedBlocks.stream()
			.filter(block -> shouldIncludeBlock(block, query))
			.limit((int)maxResults.getValue())
			.collect(Collectors.toList());
	}
	
	private boolean shouldIncludeBlock(Block block, String query)
	{
		String blockName = block.getTranslationKey().toLowerCase();
		
		// Filter by category settings
		if(blockName.contains("stone") && !includeStoneVariants.isChecked())
			return false;
		if(blockName.contains("dirt") && !includeDirtVariants.isChecked())
			return false;
		if(blockName.contains("wood") && !includeWoodVariants.isChecked())
			return false;
		if(blockName.contains("ore") && !includeOreBlocks.isChecked())
			return false;
		
		// Apply search filter
		if(!query.isEmpty() && !blockName.contains(query))
			return false;
		
		return true;
	}
	
	public List<Block> getFilteredBlocks()
	{
		return new ArrayList<>(filteredBlocks);
	}
	
	public void addSelectedBlock(Block block)
	{
		if(!selectedBlocks.contains(block))
			selectedBlocks.add(block);
	}
	
	public void removeSelectedBlock(Block block)
	{
		selectedBlocks.remove(block);
	}
	
	public boolean isBlockSelected(Block block)
	{
		return selectedBlocks.contains(block);
	}
	
	public String getSearchQuery()
	{
		return searchQuery.getValue();
	}
	
	public void setSearchQuery(String query)
	{
		searchQuery.setValue(query);
		updateFilteredBlocks();
	}
}
