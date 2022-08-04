package com.spring.koction.service.item.Impl;


import java.util.List;

import com.spring.koction.entity.Itemq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.spring.koction.entity.Item;
import com.spring.koction.entity.ItemCategory;
import com.spring.koction.entity.ItemFile;
import com.spring.koction.mapper.ItemMapper;
import com.spring.koction.repository.ItemCategoryRepository;
import com.spring.koction.repository.ItemFileRepository;
import com.spring.koction.repository.ItemRepository;
import com.spring.koction.repository.ItemqRepository;
import com.spring.koction.service.item.ItemService;

@Service
public class ItemServiceImpl implements ItemService{
	@Autowired
	ItemRepository itemRepository;
	
	@Autowired
	ItemqRepository itemqRepository;

	@Autowired
	ItemMapper itemMapper;
	
	@Autowired
	ItemFileRepository itemFileRepository;
	
	@Autowired
	ItemCategoryRepository itemCategoryRepository;


	@Override
	public Page<Item> getItemList(Item item, Pageable pageable) {
		return null;
	}



	@Override
	public Item getMyItem(int itemNo) {
		return itemRepository.findById(itemNo).get();
	}

	@Override
	public void updateItem(Item item) {
		itemRepository.save(item);
	}

	@Override
	public List<ItemFile> getItemFileList(int itemNo) {
		return null;

	}
	@Override
	public int regitserItem(Item item) {
		int itemNo = itemMapper.getNextItemNo();
		item.setItemNo(itemNo);
		itemRepository.save(item);
		itemRepository.flush();
		return itemNo;
	}

	@Override
	public void registerItemFile(List<ItemFile> fileList) {
		for(ItemFile itemFile : fileList) {
			itemFile.setItemfileNo(itemFileRepository.selectNextItemNoByItemItemNo(itemFile.getItem().getItemNo()));
			itemFileRepository.save(itemFile);
		}
	}

	@Override
	public List<Item> findCategory(int categoryNo) {
		// TODO Auto-generated method stub
		return itemRepository.findByItemCategoryCategoryNo(categoryNo);
	}

	@Override
	public List<ItemCategory> findCategory() {
		// TODO Auto-generated method stub
		return itemCategoryRepository.findAll();
	}



	@Override
	public List<ItemFile> findItemFilesByItemNo(int itemNo) {
		return itemFileRepository.findByItemItemNo(itemNo);
	}

	@Override
	public List<Itemq> selectInquryList() {
		return itemqRepository.findAll();
	}

//	@Override
//	public int insertInquryList(Itemq itemq) {
//		int itemqNo = itemMapper.getNextInquryNo();
//
////		itemq.setItem();
//		itemq.setItemqNo(itemqNo);
////		itemMapper.insertInquryList();
//		itemqRepository.save(itemq);
//		itemqRepository.flush();
//		return itemq.getItemqNo();
//	}

	@Override
	public void insertInqury(Itemq itemq) {
//		itemMapper.insertInquery(itemq);
		itemqRepository.save(itemq);
	}


}
