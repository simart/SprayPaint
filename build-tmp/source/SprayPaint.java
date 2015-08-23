import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class SprayPaint extends PApplet {



int theColor = color(255);
boolean mirrorsOn = false;
int radiusSize = 20;
float verticalE = 0;
public void setup()
{
  size(640,490);
  background(0);
  textAlign(CENTER);
}
public void draw()
{

  fill(theColor);
  noStroke();
  if(verticalE >= 1)
    verticalE = .9999999f;
  if(verticalE <= -1)
    verticalE = -.9999999f;
  
  if(mousePressed == true && mouseY <= 480)
  {
    for(int i = 0; i < 10; i++)
    {
      float theta = random(0,2*PI);
      float radius = random(0,radiusSize)*1.0f/(1.0f-verticalE*sin(theta));
      int x = mouseX + PApplet.parseInt(cos(theta)*radius);
      int y = mouseY + PApplet.parseInt(sin(theta)*radius);
      ellipse(x,y,2,2);
      if(mirrorsOn == true)
      {
        ellipse(640-x,y,2,2);
        ellipse(x,480-y,2,2);
        ellipse(640-x,480-y,2,2);
      }
    }
  }
  fill(0);
  stroke(0);
  rect(0,470,640,20);
  fill(255);
  if(verticalE > .9f)
    verticalE = 1;
  if(verticalE < -.9f)
    verticalE = -1;
  text("eccentricity " + (PApplet.parseInt(verticalE*10 + .0000001f)/10.0f), 320, 485);
}

public void keyPressed()
{
  if(key == 'm')
   mirrorsOn = !mirrorsOn;
  else if(key == 'e')
   background(0);
  else if(key == '+' && verticalE < 1)
    verticalE+=.1f;
  else if(key == '-' && verticalE > -1)
    verticalE-=.1f;  
}
public void mouseWheel(MouseEvent event) {
  if(event.getCount() < 0 && verticalE > -1)
    verticalE-=.1f;
  else if(event.getCount() > 0 && verticalE < 1)
    verticalE+=.1f;  

}
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "SprayPaint" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
