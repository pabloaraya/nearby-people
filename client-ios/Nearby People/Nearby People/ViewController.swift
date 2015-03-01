//
//  ViewController.swift
//  Nearby People
//
//  Created by Camila Eyzaguirre on 2/28/15.
//  Copyright (c) 2015 Camila Eyzaguirre. All rights reserved.
//

import UIKit
import Foundation

class ViewController: UIViewController, FBLoginViewDelegate {

    //Facebook button
    let loginView = FBLoginView()
    
    // Session of user
    let defaults = NSUserDefaults.standardUserDefaults()

    override func viewDidLoad() {
        super.viewDidLoad()
        println("hola")
        
        // Align the button in the center horizontally
        loginView.frame = CGRectOffset(
            loginView.frame,
            (self.view.center.x - (loginView.frame.size.width / 2)),
            (self.view.center.y - (loginView.frame.size.height / 2))
        );
        
        loginView.delegate = self
        loginView.readPermissions = ["public_profile", "email", "user_friends"]
        
        self.view.addSubview(loginView)
        // Do any additional setup after loading the view, typically from a nib.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }

    
    func loginViewFetchedUserInfo(loginView : FBLoginView!, user: FBGraphUser){
        println("loginViewFetchedUserInfo")
        self.defaults.setObject(user.objectID,  forKey: "fb_objectID")
        self.defaults.setObject(user.username,  forKey: "fb_username")
        self.defaults.setObject(user.name,      forKey: "fb_name")
        self.defaults.setObject(user.location,  forKey: "fb_location")
        self.defaults.setObject(user.link,      forKey: "fb_link")
        self.defaults.setObject(user.birthday,  forKey: "fb_birthday")
        self.defaults.setObject(user.description,   forKey: "fb_description")
        self.defaults.setObject(user.first_name,forKey: "fb_first_name")
        self.defaults.setObject(user.last_name, forKey: "fb_last_name")
        self.defaults.setObject(user.middle_name,   forKey: "fb_middle_name")
        self.defaults.setObject(user.hash,      forKey: "fb_hash")
        goToHome()
    }
    
    func loginViewShowingLoggedOutUser(loginView : FBLoginView!) {
        println("loginViewShowingLoggedOutUser")
        println("User Logged Out")
        logout()
    }
    
    func loginViewShowingLoggedInUser(loginView : FBLoginView!) {
        println("loginViewShowingLoggedInUser")
        println("User Logged In")
    }
    
    func loginView(loginView : FBLoginView!, handleError:NSError) {
        println("loginView")
        println("Error: \(handleError.localizedDescription)")
    }
    
    func logout() {
        for key in NSUserDefaults.standardUserDefaults().dictionaryRepresentation().keys {
            NSUserDefaults.standardUserDefaults().removeObjectForKey(key.description)
        }
        println(NSUserDefaults.standardUserDefaults().dictionaryRepresentation().keys.array.count)
    }
    
    func goToHome() {
       self.performSegueWithIdentifier("goToHome", sender: self)
    }
}

