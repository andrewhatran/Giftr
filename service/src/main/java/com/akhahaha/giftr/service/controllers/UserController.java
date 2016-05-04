package com.akhahaha.giftr.service.controllers;

import com.akhahaha.giftr.service.data.dao.DAOManager;
import com.akhahaha.giftr.service.data.dao.UserDAO;
import com.akhahaha.giftr.service.data.models.User;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 * User service controller
 */
@RestController
@RequestMapping("/user")
public class UserController {
    private UserDAO userDAO = (UserDAO) DAOManager.getInstance().getDAO(DAOManager.DAOType.USER);

    /**
     * Creates, inserts, or updates the current User
     */
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> upsertCurrentUser(
            @RequestBody(required = false) User userInput) {
        // TODO Validate authorization
        // TODO If authorization corresponds to existing user, update current User

        // Create or insert new User
        User user = userInput != null ? userInput : new User();
        Integer userID = userDAO.insertUser(user);
        user = userDAO.findUserByID(userID);

        // Return location and result
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{userID}")
                .buildAndExpand(userID).toUri());
        return new ResponseEntity<>(user, headers, HttpStatus.CREATED);
    }

    /**
     * Returns the current User
     */
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> getCurrentUser() {
        // TODO Validate authorization

        // TODO Return location and result
        return new ResponseEntity<>(null, new HttpHeaders(), HttpStatus.NOT_IMPLEMENTED);
    }

    /**
     * Returns the specified User
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> getUser(
            @PathVariable("id") Integer userID) {
        // TODO Validate authorization

        User user = userDAO.findUserByID(userID);
        if (user == null) {
            throw new UserNotFoundException(userID);
        }

        // Return location and result
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ServletUriComponentsBuilder
                .fromCurrentRequest()
                .buildAndExpand(userID).toUri());
        return new ResponseEntity<>(user, headers, HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    private class UserNotFoundException extends RuntimeException {
        UserNotFoundException(Integer userId) {
            super("Could not find user '" + userId + "'.");
        }
    }
}
