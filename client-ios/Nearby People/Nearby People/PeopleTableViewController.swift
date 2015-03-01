//
//  PeopleTableViewController.swift
//  Nearby People
//
//  Created by Camila Eyzaguirre on 2/28/15.
//  Copyright (c) 2015 Camila Eyzaguirre. All rights reserved.
//
import UIKit
import Foundation

class PeopleTableViewController: UITableViewController {
    
    
    var items: [String] = []
    
    let defaults = NSUserDefaults.standardUserDefaults()
    
    // opts can be omitted, will use default values
    let socket = SocketIOClient(socketURL: "http://54.86.110.206", opts: [
        "reconnects": true, // default true
    //        "reconnectAttempts": 5, // default -1 (infinite tries)
    //        "reconnectWait": 5, // default 10
    //        "nsp": "swift" // connects to the specified namespace. Default is /
    ])

        
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
        
        tableView.separatorStyle = UITableViewCellSeparatorStyle.None
        tableView.registerClass(UITableViewCell.self, forCellReuseIdentifier: "cell")
        
        if let id = defaults.stringForKey("fb_objectID")
        {
            println("ID es "+id)
        }else
        {
            println("Sin ID")
        }
        //message, mine true, user_id, name
        
        // Socket Events
        socket.on("message") {data, ack in
            println("socket message")
            if let json = data?[0] as? NSDictionary {
                println(json["message"]!) // foo bar
                self.items.append(json["message"] as String)
                self.addRow()
            }
        }
        socket.on("connect") {data, ack in
            println("socket connected")
            println(data)
        }
        
        // Connecting
        socket.connect()
        
        // Uncomment the following line to preserve selection between presentations
        self.clearsSelectionOnViewWillAppear = false
        
        // Uncomment the following line to display an Edit button in the navigation bar for this view controller.
        self.navigationItem.rightBarButtonItem = self.editButtonItem()

        
    }
    
    func addRow()
    {
       
        let lastIndexPath = NSIndexPath(forRow: items.count - 1, inSection: 0)
        tableView.insertRowsAtIndexPaths([lastIndexPath], withRowAnimation: .Automatic)
    }
    
    override func numberOfSectionsInTableView(tableView: UITableView) -> Int {
        // Return the number of sections.
        return 1
    }
        
    override func tableView(tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return self.items.count
    }
        
    override func tableView(tableView: UITableView, cellForRowAtIndexPath indexPath: NSIndexPath) -> UITableViewCell {
            
        var cell:UITableViewCell = tableView.dequeueReusableCellWithIdentifier("cell") as UITableViewCell
            
        cell.textLabel?.text = self.items[indexPath.row]
            
        return cell
    }
        
    override func tableView(tableView: UITableView, didSelectRowAtIndexPath indexPath: NSIndexPath) {
        println("You selected cell #\(indexPath.row)!")
        socket.emit("message", [
            "message": "Hello World",
            "mine": true,
            "user_id":"3",
            "name":"Camila",
            "avatar":"https://fbcdn-profile-a.akamaihd.net/hprofile-ak-xfp1/v/t1.0-1/p50x50/10920927_10205160136019648_187459038892401045_n.jpg?oh=cc2d9c945c81f9c0f770b1b4a7b19bd8&oe=554D8A95&__gda__=1434244879_9cf73d67774411ba6bc04f33e8b35f99"
            ])
    }
        
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }

}
