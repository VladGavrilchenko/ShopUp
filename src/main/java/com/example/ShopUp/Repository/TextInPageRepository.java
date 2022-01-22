package com.example.ShopUp.Repository;

import com.example.ShopUp.model.TextInPage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TextInPageRepository extends JpaRepository<TextInPage,Long> {

    TextInPage findTextInPageByNameTextEquals(String nameText);
}
