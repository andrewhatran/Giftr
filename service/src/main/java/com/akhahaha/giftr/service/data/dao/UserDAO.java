package com.akhahaha.giftr.service.data.dao;

import com.akhahaha.giftr.service.data.models.Gender;
import com.akhahaha.giftr.service.data.models.GiftType;
import com.akhahaha.giftr.service.data.models.User;
import com.akhahaha.giftr.service.data.models.UserStatus;

/**
 * User DAO interface
 * Created by Alan on 4/29/2016.
 */
public interface UserDAO extends DAO {
    /**
     * Inserts a new User
     * @param user User data to insert
     * @return The generated User ID
     */
    Integer insertUser(User user);

    void updateUser(User user);

    User findUserByID(int userID);

    UserStatus findUserStatusByID(int userStatusID);

    Gender findGenderByID(int genderID);

    GiftType findGiftTypeByID(int giftTypeID);
}
