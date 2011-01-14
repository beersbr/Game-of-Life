import processing.core.*; 
import processing.xml.*; 

import java.applet.*; 
import java.awt.Dimension; 
import java.awt.Frame; 
import java.awt.event.MouseEvent; 
import java.awt.event.KeyEvent; 
import java.awt.event.FocusEvent; 
import java.awt.Image; 
import java.io.*; 
import java.net.*; 
import java.text.*; 
import java.util.*; 
import java.util.zip.*; 
import java.util.regex.*; 

public class game_of_life extends PApplet {

/* game of life */

int window_width = 500;
int window_height = 500;

int cell_size = 25;
int[][] planet = new int[20][20];

static int dead = 0;
static int alive = 1;
static int growing = 3;
static int dying = 4;

public void setup_planet(){
  for(int i = 0; i < 20; i++)
    for(int j = 0; j < 20; j++)
      planet[i][j] = 0;
}

public void play_game(){
  for(int i=0; i<20; i++){
    for(int j=0; j<20; j++){

      int neighbors = 0;
      
      if(i < 19){
        if(planet[i+1][j] == alive || planet[i+1][j] == dying){
          neighbors++;
        }
      }
      
      if(i < 19 && j < 19){
        if(planet[i+1][j+1] == alive || planet[i+1][j+1] == dying){
          neighbors++;
        }
      }
      
      if(j < 19){
        if(planet[i][j+1] == alive || planet[i][j+1] == dying){
          neighbors++;
        }
      }
      
      if(i > 0 && j < 10){
        if(planet[i-1][j+1] == alive || planet[i-1][j+1] == dying){
          neighbors++;
        }
      }
      
      if(i > 0){
        if(planet[i-1][j] == alive || planet[i-1][j] == dying){
          neighbors++;
        }
      }
      
      if(i > 0 && j > 0){
        if(planet[i-1][j-1] == alive || planet[i-1][j-1] == dying){
          neighbors++;
        }
      }
      
      if(j > 0){
        if(planet[i][j-1] == alive || planet[i][j-1] == dying){
          neighbors++;
        }
      }
      
      if(i < 19 && j > 0){
        if(planet[i+1][j-1] == alive || planet[i+1][j-1] == dying){
          neighbors++;
        }
      }
      
      if(planet[i][j] == alive && neighbors < 2){
        planet[i][j] = dying;
      }
      if(planet[i][j] == alive && neighbors > 3){
        planet[i][j] = dying;
      }
      if(planet[i][j] == alive && (neighbors == 2 || planet[i][j] == dead && neighbors == 3)){
        planet[i][j] = alive;
      }
      if(planet[i][j] == dead && neighbors == 3){
        planet[i][j] = growing;
      }
      
    }
  }
  
  for(int i=0; i<20; i++){
    for(int j=0; j<20; j++){
      
      if(planet[i][j] == growing){
        planet[i][j] = alive;
      }
      if(planet[i][j] == dying){
        planet[i][j] = dead;
      }
        
    }
  }
  
}

public void draw_cells(){
  
  for(int i=0; i<20; i++){
    for(int j=0; j<20; j++){
      
      if(planet[i][j] == alive){
        fill(255);
        rect(i*25, j*25, 25, 25);
      }
      if(planet[i][j] == dead){
        fill(0);
        rect(i*25, j*25, 25, 25);
      }
      
    }
  }
  
}

public void draw_graph(){
  for(int i = 0; i < 20; i++){
    line(0, i*25, window_height, i*25);
    line(i*25, 0, i*25, window_width);
  }
}

public void mousePressed(){
  int cell_x = mouseX / 25;
  int cell_y = mouseY / 25;
  
  fill(255, 255, 255);
  rect(cell_x*25, cell_y*25, 25, 25);
  
  if(planet[cell_x][cell_y] == alive){
    planet[cell_x][cell_y] = dead;
  }
  else{
    planet[cell_x][cell_y] = alive;
  }
  
}

public void setup(){
  size(window_width, window_height);
  background(0, 0, 0);
  stroke(255, 255, 255);
  frameRate(8);
  
  setup_planet();
  draw_graph();
}

boolean playing = false;

public void keyPressed(){
  int keyIndex = -1;
  
  if(key == 'r'){
    playing = true;
    println("Started!");
  }
    
  if(key == 's'){
    playing = false;
    println("Stopped!");
  }
}

public void draw(){
  if(playing){
    play_game();
    draw_cells();
  }
}
  static public void main(String args[]) {
    PApplet.main(new String[] { "--bgcolor=#FFFFFF", "game_of_life" });
  }
}
