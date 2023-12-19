package com.tp.CRUD.service.costumer.wishlist;

import com.tp.CRUD.request.WishlistDto;

import java.util.List;

public interface WishlistService {

    public WishlistDto addProductToWishlist(WishlistDto wishlistDto);

    public List<WishlistDto> getWishlistByCustomerId(Long customerId);
}
