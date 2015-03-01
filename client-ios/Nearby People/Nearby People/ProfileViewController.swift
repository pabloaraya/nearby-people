//
//  ProfileViewController.swift
//  Nearby People
//
//  Created by Camila Eyzaguirre on 3/1/15.
//  Copyright (c) 2015 Camila Eyzaguirre. All rights reserved.
//

import UIKit

class ProfileViewController: UIViewController {

    @IBOutlet weak var imageProfile: UIImageView!
    @IBOutlet weak var usernameProfile: UILabel!
    
    let defaults = NSUserDefaults.standardUserDefaults()
    
    override func viewDidLoad() {
        super.viewDidLoad()

        let fb_objectID = defaults.stringForKey("fb_objectID") as String!
        var fb_picture = "https://graph.facebook.com/\(fb_objectID)/picture?type=normal"
        var url = NSURL(string: fb_picture)
        let data = NSData(contentsOfURL: url!)
        imageProfile.image = UIImage(data: data!)
        
//        var fb_picture = "https://graph.facebook.com/\(user.objectID)/picture?type=normal"
//        self.defaults.setObject(fb_picture,      forKey: "fb_picture")
        
        
        usernameProfile.text = defaults.stringForKey("fb_name")
        // Do any additional setup after loading the view.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    

    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepareForSegue(segue: UIStoryboardSegue, sender: AnyObject?) {
        // Get the new view controller using segue.destinationViewController.
        // Pass the selected object to the new view controller.
    }
    */

}
