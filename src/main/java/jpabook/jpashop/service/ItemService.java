package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public void saveItem(Item item){
        itemRepository.save(item);

    }

    //병합 감지 기능 사용
    @Transactional
    public void updateItem(Long itemId, String name, int price, int stockQuantity) { //itemParam: 파리미터로 넘어온 준영속 상태의 엔티티
        Item findItem = itemRepository.findOne(itemId); //같은 엔티티를 조회한다.
        findItem.setPrice(price); //데이터를 수정한다.
        findItem.setName(name); //데이터를 수정한다.
        findItem.setStockQuantity(stockQuantity); //데이터를 수정한다.

    }

    //병합 사용
    @Transactional
    //void update(Item itemParam) { //itemParam: 파리미터로 넘어온 준영속 상태의 엔티티
    //    Item mergeItem = em.merge(item);
    //}

    public List<Item> findItems(){
        return itemRepository.findAll();
    }

    public Item findOne(Long itemId){
        return itemRepository.findOne(itemId);
    }

}
