//
//  HomeViewController.swift
//  Nearby People
//
//  Created by Camila Eyzaguirre on 2/28/15.
//  Copyright (c) 2015 Camila Eyzaguirre. All rights reserved.
//

import UIKit
import Foundation

class PeopleViewController: UIViewController {
    
    // Session of user
    let defaults = NSUserDefaults.standardUserDefaults()
    
    // opts can be omitted, will use default values
    let socket = SocketIOClient(socketURL: "https://192.168.1.194", opts: [
        "reconnects": true, // default true
//        "reconnectAttempts": 5, // default -1 (infinite tries)
//        "reconnectWait": 5, // default 10
//        "nsp": "swift" // connects to the specified namespace. Default is /
        ])
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        if let id = defaults.stringForKey("fb_objectID")
        {
            println("ID es "+id)
        }else
        {
            println("Sin ID")
        }
        
        // Socket Events
//        socket.on("connect") {data, ack in
//            println("socket connected")
        
//            // Sending messages
//            socket.emit("testEcho")
//            
//            socket.emit("testObject", [
//                "data": true
//                ])
//            
//            // Sending multiple items per message
//            socket.emit("multTest", [1], 1.4, 1, "true",
//                true, ["test": "foo"], "bar")
//        }
        // Connecting
        socket.connect()
        
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
