

int theColor = color(255);
boolean mirrorsOn = false;
int radiusSize = 20;
float verticalE = 0;
void setup()
{
  size(640,490);
  background(0);
  textAlign(CENTER);
}
void draw()
{

  fill(theColor);
  noStroke();
  // if(verticalE >= 1)
  //   verticalE = .9999999;
  // if(verticalE <= -1)
  //   verticalE = -.9999999;
  
  if(mousePressed == true && mouseY <= 480)
  {
    for(int i = 0; i < 10; i++)
    {
      float theta = random(0,2*PI);
      float radius = random(0,radiusSize)*1.0/(1.0-verticalE*sin(theta));
      int x = mouseX + int(cos(theta)*radius);
      int y = mouseY + int(sin(theta)*radius);
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
  // if(verticalE > .9)
  //   verticalE = 1;
  // if(verticalE < -.9)
  //   verticalE = -1;
  text("eccentricity " + (int(verticalE*10)/10.0), 320, 485);
}

void keyPressed()
{
  if(key == 'm')
   mirrorsOn = !mirrorsOn;
  else if(key == 'e')
   background(0);
  else if(key == '+' && verticalE < 1)
    verticalE+=.1;
  else if(key == '-' && verticalE > -1)
    verticalE-=.1;  
}
void mouseWheel(MouseEvent event) {
  if(event.getCount() < 0 && verticalE > -1)
    verticalE-=.1;
  else if(event.getCount() > 0 && verticalE < 1)
    verticalE+=.1;  

}
