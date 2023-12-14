package com.self.management.self_management.Model;

import javafx.geometry.Pos;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

public class CustomNotification {
   public CustomNotification()
   {
       Notifications.create()
               .title("Hii")
               .darkStyle()
               .hideAfter(Duration.seconds(3))
               .position(Pos.BOTTOM_RIGHT)
               .graphic(new AnchorPane())
               .text("Are you okky?")
               .show();
   }
}
