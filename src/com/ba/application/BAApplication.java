package com.ba.application;

import com.ba.field.screen.IntroScreen;

import net.rim.device.api.ui.UiApplication;

public class BAApplication extends UiApplication
{
      public BAApplication(){        
        pushScreen(new IntroScreen());
    }    
}
