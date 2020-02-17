//The MIT License (MIT) - See Licence.txt for details

//Copyright (c) 2013 Mick Grierson, Matthew Yee-King, Marco Gillies

int tvx, tvy;
int animx, animy;
int deck1x, deck1y;
int deck2x, deck2y;
int marginBtn = 70;
float xPos1;
float yPos1;
float xPos2;
float yPos2;
boolean deck1Playing = false;
boolean deck2Playing = false;
float rotateDeck1 = 0;
float rotateDeck2 = 0;
float currentFrame = 0;
int margin = width/40;
PImage [] images;
PImage [] recordPlayer;
PImage TV;
PImage back;
Maxim maxim;
WavetableSynth synth;
AudioPlayer deck1;
AudioPlayer deck2;
AudioPlayer player1;
AudioPlayer player2;
AudioPlayer player3;
AudioPlayer player4;qui
boolean btn006 = false;
boolean rav1 = false;
boolean rav2 = false;
boolean rav3 = false;
boolean voc1 = false;
boolean voc2 = false;
boolean voc3 = false;
boolean cla1 = false;
boolean cla2 = false;
boolean cla3 = false;
boolean h1 = false;
boolean h2 = false;
boolean h3 = false;
boolean amb1 = false;
boolean amb2 = false;
boolean amb3 = false;
Slider ap, av, bp, bv, dp, dv, kp, kv, rp, rv, sp, sv, d1v, d1p, d2v, d2p;
Slider dt1, dg1, a1, r1, f1, q1;
Slider vs;

MultiSlider seq; 

Float AP = 1.0, AV = 1.0, BP = 1.0, BV = 1.0, DP = 1.0, DV = 1.0, KP = 1.0, KV = 1.0, RP = 1.0, RV = 1.0, SP = 1.0, SV = 1.0;
Float D1V = 1.0, D1P = 1.0, D2V = 1.0, D2P = 1.0, VS = 1.0;
Float fc1, res1=20.0, attack1, release1;

int playhead;
int[] notes = {
  0, 0, 0, 12, 12, 12, 10, 10, 10, 7, 7, 7, 6, 6, 3, 3
};
boolean playit=false;
float[] wavetable = new float[514];

AudioPlayer sample1;
AudioPlayer sample2; 
AudioPlayer sample3; 
AudioPlayer sample4;

boolean[] track1;
boolean[] track2;
boolean[] track3;
boolean[] track4;

int numBeats;
int currentBeat;
int buttonWidth;
int buttonHeight;
int beatH;
int BPM=60;

int posX1;
int posX2;
boolean playit1;
boolean playit2;

void setup()
{
  size(1024,1024);
  imageMode(CENTER);
  
  numBeats = 16;
  currentBeat = 0;
  buttonWidth = width/numBeats;
  buttonHeight = 55;
  beatH = 280;
  
  images = loadImages("Animation_data/movie", ".jpg", 170);
  
  recordPlayer = loadImages("black-record_", ".png", 36);
  maxim = new Maxim(this);
  
  sample1 = maxim.loadFile("bd1.wav");
  sample1.volume(1);
  sample1.setLooping(false);
  sample2 = maxim.loadFile("sn1.wav");
  sample2.setLooping(false);
  sample2.volume(1);
  sample3 = maxim.loadFile("hh1.wav");
  sample3.volume(1);
  sample3.setLooping(false);
  sample4 = maxim.loadFile("sn2.wav");
  sample4.setLooping(false);
  
  track1 = new boolean[numBeats];
  track2 = new boolean[numBeats];
  track3 = new boolean[numBeats];
  track4 = new boolean[numBeats];
  
  deck1 = maxim.loadFile("beat1.wav");
  deck1.setAnalysing(true);
  deck1.setLooping(true);
  xPos1 = width/3-40;
  posX1 = 10;
  playit1 = false;
  
  
  deck2 = maxim.loadFile("beat2.wav");
  deck2.setAnalysing(true);
  deck2.setLooping(true);
  xPos2 = 0;
  posX2 = width/2+10;
  playit2 = false;
  
  player1 = maxim.loadFile("Arpeggio01.wav");
  player1.setAnalysing(true);
  player1.setLooping(true);
  player2 = maxim.loadFile("BassShot01.wav");
  player2.setAnalysing(true);
  player2.setLooping(true);
  player3 = maxim.loadFile("Drums01.wav");
  player3.setAnalysing(true);
  player3.setLooping(true);
  player4 = maxim.loadFile("Kick01.wav");
  player4.setAnalysing(true);
  player4.setLooping(true);
  player5 = maxim.loadFile("Reece01.wav");
  player5.setAnalysing(true);
  player5.setLooping(true);
  player6 = maxim.loadFile("Snare01.wav");
  player6.setAnalysing(true);
  player6.setLooping(true);
  rave1 = maxim.loadFile("ravestab01.wav");
  rave1.setLooping(false);
  rave2 = maxim.loadFile("ravestab02.wav");
  rave2.setLooping(false);
  rave3 = maxim.loadFile("ravestab03.wav");
  rave3.setLooping(false);
  vocal1 = maxim.loadFile("vocal01.wav");
  vocal1.setLooping(false);
  vocal2 = maxim.loadFile("vocal02.wav");
  vocal2.setLooping(false);
  vocal3 = maxim.loadFile("vocal03.wav");
  vocal3.setLooping(false);
  clap1 = maxim.loadFile("clap01.wav");
  clap1.setLooping(false);
  clap2 = maxim.loadFile("clap02.wav");
  clap2.setLooping(false);
  clap3 = maxim.loadFile("clap03.wav");
  clap3.setLooping(false);
  hc1 = maxim.loadFile("hc01.wav");
  hc1.setLooping(false);
  hc2 = maxim.loadFile("hc02.wav");
  hc2.setLooping(false);
  hc3 = maxim.loadFile("hc03.wav");
  hc3.setLooping(false);
  ambience1 = maxim.loadFile("ambience01.wav");
  ambience1.setLooping(false);
  ambience2 = maxim.loadFile("ambience02.wav");
  ambience2.setLooping(false);
  ambience3 = maxim.loadFile("ambience03.wav");
  ambience3.setLooping(false);
  volumeAdjustD = 0;
  ap = new Slider("PITCH", 50, 0, 100, 250, 10, 200, 20, HORIZONTAL);
  av = new Slider("VOL", 50, 0, 100, 550, 10, 200, 20, HORIZONTAL);
  bp = new Slider("PITCH", 50, 0, 100, 250, 10+25, 200, 20, HORIZONTAL);
  bv = new Slider("VOL", 50, 0, 100, 550, 10+25, 200, 20, HORIZONTAL);
  dp = new Slider("PITCH", 50, 0, 100, 250, 10+25*2, 200, 20, HORIZONTAL);
  dv = new Slider("VOL", 50, 0, 100, 550, 10+25*2, 200, 20, HORIZONTAL);
  kp = new Slider("PITCH", 50, 0, 100, 250, 10+25*3, 200, 20, HORIZONTAL);
  kv = new Slider("VOL", 50, 0, 100, 550, 10+25*3, 200, 20, HORIZONTAL);
  rp = new Slider("PITCH", 50, 0, 100, 250, 10+25*4, 200, 20, HORIZONTAL);
  rv = new Slider("VOL", 50, 0, 100, 550, 10+25*4, 200, 20, HORIZONTAL);
  sp = new Slider("PITCH", 50, 0, 100, 250, 10+25*5, 200, 20, HORIZONTAL);
  sv = new Slider("VOL", 50, 0, 100, 550, 10+25*5, 200, 20, HORIZONTAL);
  d1p = new Slider("   P", 50, 0, 100, 260, 520, 40, 180, UPWARDS);
  d1v = new Slider("  V", 50, 0, 100, 310, 520, 40, 180, UPWARDS);
  d2v = new Slider("  V", 50, 0, 100, 670, 520, 40, 180, UPWARDS);
  d2p = new Slider("  P", 50, 0, 100, 720, 520, 40, 180, UPWARDS);
  
  dt1 = new Slider("delay", 0, 0, 100, 360, 535, 100, 20, HORIZONTAL);
  a1 = new Slider("attack", 0, 0, 100, 360, 570, 100, 20, HORIZONTAL);
  r1 = new Slider("rel.", 0, 0, 100, 360, 605, 100, 20, HORIZONTAL);
  f1 = new Slider("filter", 0, 0, 100, 360, 640, 100, 20, HORIZONTAL);
  q1 = new Slider("res", 0, 0, 100, 360, 675, 100, 20, HORIZONTAL);
  
  vs = new Slider("vol", 0, 0, 100, 360, 720, 240, 20, HORIZONTAL);
  
  synth = maxim.createWavetableSynth(514);
  
  seq = new MultiSlider(notes.length, 0, 256, width/2-40, 530, 12, 170, UPWARDS);
}

void draw()
{
  back = loadImage("back.jpg");
  background(back); 
  imageMode(CENTER);
  //synth.play();
  
  frameRate(60);
  
  fill(50,200);
  rect(5,5,width-10,155);
  
  fill(50,200);
  rect(5,520,width-10,230);
  
  
  fill(0);
  rect(355, 520,1,height/2);
  fill(0);
  rect(665, 520,1,height/2);
  
  
  fill(255);
  rect(670, 710, 40,35);
  textSize(12);
  fill(0);
  text(D2V, 670, 735);
  
  fill(255);
  rect(720, 710, 40,35);
  textSize(12);
  fill(0);
  text(D2P, 720, 735);
  
  fill(255);
  rect(260, 710, 40,35);
  textSize(12);
  fill(0);
  text(D1P, 260, 735);
  
  fill(255);
  rect(310, 710, 40,35);
  textSize(12);
  fill(0);
  text(D1V, 310, 735);
  
  fill(255,127);
  rect(0,270,width,240);
  
  fill (150,180);
  rect(357,520,308,height);
  
  if (btnP){
    fill(255, 0, 0); 
  }
  else {
    fill(0, 255, 0);
  }
  rect(width/2+100, 710, 50, 40);  
  
  if (btnP){
    textSize(15);
    fill(0);
    text("STOP", width/2+107, 735);
  } else {
    textSize(15);
    fill(0);
    text("PLAY", width/2+107, 735);
  }
  ////////////////////////////////////////1
  textSize(20);
  fill(255);
  text("ARPEGGIO", 10, 28); 
  if (btn01){
    fill(255, 0, 0); 
  }
  else {
    fill(0, 255, 0);
  }
  rect((width/6), 10, width/16, 20);
  
  fill(255);
  rect(470, 10, width/16, 20);
  textSize(20);
  fill(0);
  text(AP, 468, 28);
  
  fill(255);
  rect(770, 10, width/16, 20);
  textSize(20);
  fill(0);
  text(AV, 768, 28);
  
  if (btn01) {
    pow1 = player1.getAveragePower();
    fill(255, 205, pow1*255);
    rect((width/1.2+15), 10, volumeAdjust1*pow1*width/4+45, 20);
  } 
  ////////////////////////////////////////2
  textSize(20);
  fill(255);
  text("BASS SHOT", 10, 28+25); 
  if (btn02){
    fill(255, 0, 0); 
  }
  else {
    fill(0, 255, 0);
  }
  rect((width/6), 10+25, width/16, 20);
  
  fill(255);
  rect(470, 10+25, width/16, 20);
  textSize(20);
  fill(0);
  text(BP, 468, 28+25);
  
  fill(255);
  rect(770, 10+25, width/16, 20);
  textSize(20);
  fill(0);
  text(BV, 768, 28+25);
  
  if (btn02) {
    pow2 = player2.getAveragePower();
    fill(255, 205, pow2*255);
    rect((width/1.2+15), 10+25, volumeAdjust2*pow2*width/4+45, 20);
  } 
  ////////////////////////////////////////3
  textSize(20);
  fill(255);
  text("DRUMS", 10, 28+25*2); 
  if (btn03){
    fill(255, 0, 0); 
  }
  else {
    fill(0, 255, 0);
  }
  rect((width/6), 10+25*2, width/16, 20);
  
  fill(255);
  rect(470, 10+25*2, width/16, 20);
  textSize(20);
  fill(0);
  text(DP, 468, 28+25*2);
  
  fill(255);
  rect(770, 10+25*2, width/16, 20);
  textSize(20);
  fill(0);
  text(DV, 768, 28+25*2);
  
  if (btn03) {
    pow3 = player3.getAveragePower();
    fill(255, 205, pow3*255);
    rect((width/1.2+15), 10+25*2, volumeAdjust3*pow3*width/4+45, 20);
  }  
 ////////////////////////////////////////4
  textSize(20);
  fill(255);
  text("KICK", 10, 28+25*3); 
  if (btn04){
    fill(255, 0, 0); 
  }
  else {
    fill(0, 255, 0);
  }
  rect((width/6), 10+25*3, width/16, 20);
  
  fill(255);
  rect(470, 10+25*3, width/16, 20);
  textSize(20);
  fill(0);
  text(KP, 468, 28+25*3);
  
  fill(255);
  rect(770, 10+25*3, width/16, 20);
  textSize(20);
  fill(0);
  text(KV, 768, 28+25*3);
  
  if (btn04) {
    pow4 = player4.getAveragePower();
    fill(255, 205, pow4*255);
    rect((width/1.2+15), 10+25*3, volumeAdjust4*pow4*width/4+45, 20);
  }
  ////////////////////////////////////////5
  textSize(20);
  fill(255);
  text("REECE", 10, 28+25*4); 
  if (btn05){
    fill(255, 0, 0); 
  }
  else {
    fill(0, 255, 0);
  }
  rect((width/6), 10+25*4, width/16, 20);
  
  fill(255);
  rect(470, 10+25*4, width/16, 20);
  textSize(20);
  fill(0);
  text(RP, 468, 28+25*4);
  
  fill(255);
  rect(770, 10+25*4, width/16, 20);
  textSize(20);
  fill(0);
  text(RV, 768, 28+25*4);
  
  if (btn05) {
    pow5 = player5.getAveragePower();
    fill(255, 205, pow5*255);
    rect((width/1.2+15), 10+25*4, volumeAdjust5*pow5*width/4+45, 20);
  }
   ////////////////////////////////////////6
  textSize(20);
  fill(255);
  text("SNARE", 10, 28+25*5); 
  if (btn06){
    fill(255, 0, 0); 
  }
  else {
    fill(0, 255, 0);
  }
  rect((width/6), 10+25*5, width/16, 20);
  
  fill(255);
  rect(470, 10+25*5, width/16, 20);
  textSize(20);
  fill(0);
  text(SP, 468, 28+25*5);
  
  fill(255);
  rect(770, 10+25*5, width/16, 20);
  textSize(20);
  fill(0);
  text(SV, 768, 28+25*5);
  
  if (btn06) {
    pow6 = player6.getAveragePower();
    fill(255, 205, pow6*255);
    rect((width/1.2+15), 10+25*5, volumeAdjust6*pow6*width/4+45, 20);
  }
 ////////////////////////DISPLAY SLIDERS
    if (ap.get() !=0 ) {
    AP=ap.get()*2/100;
    player1.speed(AP);
   }
   ap.display();
   if (av.get() !=0 ) {
    AV=av.get()*2/100;
    player1.volume(AV);
   }
   av.display();
   
   if (bp.get() !=0 ) {
    BP=bp.get()*2/100;
    player2.speed(BP);
   }
   bp.display();
   if (bv.get() !=0 ) {
    BV=bv.get()*2/100;
    player2.volume(BV);
   }
   bv.display();
   
   if (dp.get() !=0 ) {
    DP=dp.get()*2/100;
    player3.speed(DP);
   }
   dp.display();
   if (dv.get() !=0 ) {
    DV=dv.get()*2/100;
    player3.volume(DV);
   }
   dv.display();
   
   if (kp.get() !=0 ) {
    KP=kp.get()*2/100;
    player4.speed(KP);
   }
   kp.display();
   if (kv.get() !=0 ) {
    KV=kv.get()*2/100;
    player4.volume(KV);
   }
   kv.display();
   
   if (rp.get() !=0 ) {
    RP=rp.get()*2/100;
    player5.speed(RP);
   }
   rp.display();
   if (rv.get() !=0 ) {
    RV=rv.get()*2/100;
    player5.volume(RV);
   }
   rv.display();
   
   if (sp.get() !=0 ) {
    SP=sp.get()*2/100;
    player6.speed(SP);
   }
   sp.display();
   if (sv.get() !=0 ) {
    SV=sv.get()*2/100;
    player6.volume(SV);
   }
   sv.display();

   if (d1p.get() !=0 ) {
    D1P=d1p.get()*2/100;
    deck1.speed(D1P);
   }
   d1p.display();
   
   if (d1v.get() !=0 ) {
    D1V=d1v.get()*2/100;
    deck1.volume(D1V);
   }
   d1v.display();
   
   if (d2p.get() !=0 ) {
    D2P=d2p.get()*2/100;
    deck2.speed(D2P);
   }
   d2p.display();
   
   if (d2v.get() !=0 ) {
    D2V=d2v.get()*2/100;
    deck2.volume(D2V);
   }
   d2v.display();
   
  //////////////////////////EFFECTS 
  if (f1.get()!=0) {
    fc1=f1.get()*100;
    synth.setFilter(fc1, res1);
  }
  if (dt1.get()!=0) {
    synth.setDelayTime((float) dt1.get()/50);
  }
  if (q1.get() !=0 ) {
    res1=q1.get() / 50;
    //synth.setFilter(fc1, res1);
  }
  if (a1.get()!= 0) {
    attack1=a1.get()*10;
  }
  if (r1.get() !=0 ) {
    release1=r1.get()*10;
  }
  
  if (vs.get() !=0 ) {
    VS=vs.get()*2/100;
    synth.volume(VS);
  }
  
  dt1.display();
  a1.display();
  r1.display();
  f1.display();
  q1.display();  
  seq.display(); 
  
  vs.display();
      
 ////////////////////////////RAMDOMS
  fill(255,200);
  rect(10, 170, 190, 30);

  textSize(15);
  fill(225,0,0);
  text("RaveStab Random 1-3", 25, 190);
  fill(255,90);
  rect(10, 200, 190, 50);
  
  fill(255,200);
  rect(width/2-300, 170, 190, 30);

  textSize(15);
  fill(225,0,0);
  text("Clap Random 1-3", width/2-270, 190);
  fill(255,90);
  rect(width/2-300, 200, 190, 50);
  
  fill(255,200);
  rect(width/2+100, 170, 190, 30);

  textSize(15);
  fill(225,0,0);
  text("Ambience Random 1-3", width/2+110, 190);
  fill(255,90);
  rect(width/2+100, 200, 190, 50);
  
  
  fill(255,200);
  rect(width/2-100, 170, 190, 30);

  textSize(15);
  fill(225,0,0);
  text("Hardcore Random 1-3", width/2-90, 190);
  fill(255,90);
  rect(width/2-100, 200, 190, 50);
  
 
  fill(255,200);
  rect(width/1.3+25, 170, 200, 30);
  
  textSize(15);
  fill(225,0,0);
  text("Vocals Random 1-3", 840, 190);
  fill(255,90);
  rect(width/1.3+25, 200, 200, 50);
 ///////////////////////////BEATMACHINE
 
  stroke(255);
  for (int i = 0; i < 5; i++)
    line(0, beatH+(i*buttonHeight), width, beatH+(i*buttonHeight));
  for (int i = 0; i < numBeats + 1; i++)
    line(i*width/numBeats, beatH, i*width/numBeats, beatH+(4*buttonHeight));

  fill(0, 0, 200, 120);
  rect(currentBeat*buttonWidth, beatH, buttonWidth, buttonHeight*4);

  for (int i = 0; i < numBeats; i++)
  {
    noStroke();
    fill(200, 0, 0);

    if (track1[i])
      rect(i*buttonWidth, beatH+(0*buttonHeight), buttonWidth, buttonHeight);
    if (track2[i])
      rect(i*buttonWidth, beatH+(1*buttonHeight), buttonWidth, buttonHeight);
    if (track3[i])
      rect(i*buttonWidth, beatH+(2*buttonHeight), buttonWidth, buttonHeight);
    if (track4[i])
      rect(i*buttonWidth, beatH+(3*buttonHeight), buttonWidth, buttonHeight);
  }

  playhead ++;
  if(playhead % 4 == 0){
    if (track1[currentBeat])
    {
      sample1.cue(0);
      sample1.play();
    }
    if (track2[currentBeat]) {
      sample2.cue(0);
      sample2.play();
    }
    if (track3[currentBeat]) {
      sample3.cue(0);  
      sample3.play();
    }
    if (track4[currentBeat]) {
      sample4.cue(0);
      sample4.play();
    }
    
    synth.setFrequency(mtof[notes[playhead/4%16]+30]);
    
    currentBeat++;
    if (currentBeat >= numBeats)
      currentBeat = 0; 
  }
  
  //fill(255);
  //rect(width/2-40, 455,80,50);
  //textSize(35);
  //fill(0);
  //text((int)BPM*2, width/2-35, 490);
  
  //fill(127);
  //rect(width/2-40-50, 470,40,25);
  //textSize(35);
  //fill(0);
  //text("-", width/2-35-45, 492);

  //fill(127);
  //rect(width/2+50, 470,40,25);
  //textSize(35);
  //fill(0);
  //text("+", width/2+55, 492);  
 
 ///////////////////////////DECKS
  
  if (!deck1Playing){
    fill(127,0,0);
    rect(10,540,(width/2-90)/2,height/4-50);
  }else{
    fill(255,0,0);
    rect(10,540,(width/2-90)/2,height/4-50);
  }
  if (!deck2Playing){
    fill(0,127,255);
    rect(800,540,(width/2-90)/2,height/4-50);
  }else{
    fill(127,254,254);
    rect(800,540,(width/2-90)/2,height/4-50);
  }  
  deck1x = recordPlayer[0].width/2+10;
  deck1y = 645;
  image(recordPlayer[(int) rotateDeck1], deck1x, deck1y, recordPlayer[0].width, recordPlayer[0].height);
  deck2x = width-recordPlayer[0].width/2-10;
  deck2y = 645;
  image(recordPlayer[(int) rotateDeck2], deck2x, deck2y, recordPlayer[0].width, recordPlayer[0].height);
  
  if (deck1Playing) {
    rotateDeck1 += 1*speedAdjustD1;
    if (rotateDeck1 >= recordPlayer.length) {
      rotateDeck1 = 0;
    }
  }
  if (deck2Playing) {
    rotateDeck2 += 1*speedAdjustD2;
    if (rotateDeck2 >= recordPlayer.length) {
      rotateDeck2 = 0;
    }
  }
  if (deck1Playing) {
    powD1 = deck1.getAveragePower();
    fill(255, 205, powD1*255);
    rect(width/5+25, 540, 20, (volumeAdjustD1*powD1*height/10-15)+height/10-15);
  } 
  if (deck2Playing) {
    powD2 = deck2.getAveragePower();
    fill(255, 205, powD2*255);
    rect(width/1.4+40, 540, 20, (volumeAdjustD2*powD2*height/10-15)+height/10-15);
    }   
    
}

void mouseClicked()
{
//  if(mouseX>(width/2-90)&&mouseX<(width/2-90)+40&&mouseY>470&&mouseY<495){
//  BPM -= 1;
//}
//  if(mouseX>(width/2+50)&&mouseX<(width/2+50)+40&&mouseY>470&&mouseY<495){
//  BPM += 1;
//} 





  if(dist(mouseX, mouseY, deck1x, deck1y) < recordPlayer[0].width/2){
    deck1Playing = !deck1Playing;
  }
  if (deck1Playing) {
    deck1.play();
  } 
  else {
    deck1.stop();
  }
  
  
  
  if(dist(mouseX, mouseY, deck2x, deck2y) < recordPlayer[0].width/2){
    deck2Playing = !deck2Playing;
  }
  if (deck2Playing) {
    deck2.play();
  } 
  else {
    deck2.stop();
  }
  
if(mouseX>(width/5+20)&&mouseX<(width/5+20)+(width/16)&&mouseY>15&&mouseY<15+(height/20)){
  speedAdjustD1=1.0;
  deck1.speed(speedAdjustD1);
}
if(mouseX>(width/1.4+5)&&mouseX<(width/1.4+5)+(width/16)&&mouseY>15&&mouseY<15+(height/20)){
  speedAdjustD2=1.0;
  deck2.speed(speedAdjustD2);
}
  
  //////////////////////////////////////1
  if (mouseX > width/6 && mouseX < (width/6)+(width/16) && mouseY > 10 && mouseY < 10+20) {
    btn01 = !btn01;
    if (btn01){
        player1.cue(0);
        player1.play();
    }
    else {
      player1.stop();
    }
  }
  
  if (mouseX>470 && mouseX<470+width/16 && mouseY>10 && mouseY<10+20){
    AP=1.0;
    player1.speed(AP);
    ap.set(50.0);
  }
  if (mouseX>770 && mouseX<770+width/16 && mouseY>10 && mouseY<10+20){
    AV=1.0;
    player1.volume(AV);
    av.set(50.0);
  }
  //////////////////////////////////////2
  if (mouseX > width/6 && mouseX < (width/6)+(width/16) && mouseY > 10+25 && mouseY < 10+20+25) {
    btn02 = !btn02;
    if (btn02){
        player2.cue(0);
        player2.play();
    }
    else {
      player2.stop();
    }
  }
  
  if (mouseX>470 && mouseX<470+width/16 && mouseY>10+25 && mouseY<10+20+25){
    BP=1.0;
    player2.speed(BP);
    bp.set(50.0);
  }
  if (mouseX>770 && mouseX<770+width/16 && mouseY>10+25 && mouseY<10+20+25){
    BV=1.0;
    player2.volume(BV);
    bv.set(50.0);
  }
    //////////////////////////////////////3
  if (mouseX > width/6 && mouseX < (width/6)+(width/16) && mouseY > 10+25*2 && mouseY < 10+20+25*2) {
    btn03 = !btn03;
    if (btn03){
        player3.cue(0);
        player3.play();
    }
    else {
      player3.stop();
    }
  }
  
  if (mouseX>470 && mouseX<470+width/16 && mouseY>10+25*2 && mouseY<10+20+25*2){
    DP=1.0;
    player3.speed(DP);
    dp.set(50.0);
  }
  if (mouseX>770 && mouseX<770+width/16 && mouseY>10+25*2 && mouseY<10+20+25*2){
    DV=1.0;
    player3.volume(DV);
    dv.set(50.0);
  }
      //////////////////////////////////////4
  if (mouseX > width/6 && mouseX < (width/6)+(width/16) && mouseY > 10+25*3 && mouseY < 10+20+25*3) {
    btn04 = !btn04;
    if (btn04){
        player4.cue(0);
        player4.play();
    }
    else {
      player4.stop();
    }
  }
  
  if (mouseX>470 && mouseX<470+width/16 && mouseY>10+25*3 && mouseY<10+20+25*3){
    KP=1.0;
    player4.speed(KP);
    kp.set(50.0);
  }
  if (mouseX>770 && mouseX<770+width/16 && mouseY>10+25*3 && mouseY<10+20+25*3){
    KV=1.0;
    player4.volume(KV);
    kv.set(50.0);
  }
  
      //////////////////////////////////////5
  if (mouseX > width/6 && mouseX < (width/6)+(width/16) && mouseY > 10+25*4 && mouseY < 10+20+25*4) {
    btn05 = !btn05;
    if (btn05){
        player5.cue(0);
        player5.play();
    }
    else {
      player5.stop();
    }
  }
  
  if (mouseX>470 && mouseX<470+width/16 && mouseY>10+25*4 && mouseY<10+20+25*4){
    RP=1.0;
    player5.speed(RP);
    rp.set(50.0);
  }
  if (mouseX>770 && mouseX<770+width/16 && mouseY>10+25*4 && mouseY<10+20+25*4){
    RV=1.0;
    player5.volume(RV);
    rv.set(50.0);
  }
        //////////////////////////////////////6
  if (mouseX > width/6 && mouseX < (width/6)+(width/16) && mouseY > 10+25*5 && mouseY < 10+20+25*5) {
    btn06 = !btn06;
    if (btn06){
        player6.cue(0);
        player6.play();
    }
    else {
      player6.stop();
    }
  }
  
  if (mouseX>470 && mouseX<470+width/16 && mouseY>10+25*5 && mouseY<10+20+25*5){
    SP=1.0;
    player6.speed(SP);
    sp.set(50.0);
  }
  if (mouseX>770 && mouseX<770+width/16 && mouseY>10+25*5 && mouseY<10+20+25*5){
    SV=1.0;
    player5.volume(SV);
    sv.set(50.0);
  }  
  if (mouseX>670 && mouseX<670+40 && mouseY>710 && mouseY<710+35){
    D2V=1.0;
    deck2.volume(D2V);
    d2v.set(50.0);
  }
  if (mouseX>720 && mouseX<720+40 && mouseY>710 && mouseY<710+35){
    D2P=1.0;
    deck2.speed(D2P);
    d2p.set(50.0);
  }
  if (mouseX>260 && mouseX<260+40 && mouseY>710 && mouseY<710+35){
    D1P=1.0;
    deck1.speed(D1P);
    d1p.set(50.0);
  }
  if (mouseX>310 && mouseX<310+40 && mouseY>710 && mouseY<710+35){
    D1V=1.0;
    deck1.volume(D1V);
    d1v.set(50.0);
  }
    
  ///////////////////RAMDOMS
  
  if (mouseX>10 && mouseX<10+190 && mouseY>170 && mouseY<200) {
    playRave((int) random(1, 4));
  }
  
  if (mouseX>(width/2-300) && mouseX<(width/2-300)+190 && mouseY>170 && mouseY<200) {
    playClap((int) random(1, 4));
  }
  
  if (mouseX>width/2-100 && mouseX<(width/2-100)+190 && mouseY>170 && mouseY<200) {
    playHC((int) random(1, 4));
  }
  
  if (mouseX>width/2+100 && mouseX<(width/2+100)+190 && mouseY>170 && mouseY<200) {
    playAmb((int) random(1, 4));
  }
  
  if (mouseX>width/1.3+25 && mouseX<width/1.3+25+200 && mouseY>170 && mouseY<200) {
    playVocal((int) random(1, 4));
  } 
  
/////////////////////SYNTH
  if (mouseX > width/2+100 && mouseX < width/2+150 && mouseY > 710 && mouseY < 750) {
    btnP = !btnP;
    if (btnP){
        synth.play();
    }
    else {
      synth.stop();
    }
  }

  
}

void playRave(int rave) {
  if (rave == 1) {
    rave1.cue(0);
    rave1.speed(random(0.5, 2));
    rave1.play();
    
    textSize(40);
    fill(225,225,0);
    text("1", (190/2)-70, 240);
  }
  if (rave == 2) {
    rave2.cue(0);
    rave2.speed(random(0.5, 2));
    rave2.play();
    
    textSize(40);
    fill(225,225,0);
    text("2", (190/2), 240);
    
  }
  if (rave == 3) {
    rave3.cue(0);
    rave3.speed(random(0.5, 2));
    rave3.play();
    
    textSize(40);
    fill(225,225,0);
    text("3", (190/2)+70, 240);
  }
}

void playClap(int clap) {
  if (clap == 1) {
    clap1.cue(0);
    clap1.speed(random(0.5, 2));
    clap1.play();
    
    textSize(40);
    fill(225,225,0);
    text("1", 290-70, 240);
    
  }
  if (clap == 2) {
    clap2.cue(0);
    clap2.speed(random(0.5, 2));
    clap2.play();
    
    textSize(40);
    fill(225,225,0);
    text("2", 290, 240);
    
  }
  if (clap == 3) {
    clap3.cue(0);
    clap3.speed(random(0.5, 2));
    clap3.play();
    
    textSize(40);
    fill(225,225,0);
    text("3", 290+70, 240);
  }
}

void playHC(int hc) {
  if (hc == 1) {
    hc1.cue(0);
    hc1.speed(random(0.5, 2));
    hc1.play();
    
    textSize(40);
    fill(225,225,0);
    text("1", 495 - 70, 240);
    
  }
  if (hc == 2) {
    hc2.cue(0);
    hc2.speed(random(0.5, 2));
    hc2.play();
    
    textSize(40);
    fill(225,225,0);
    text("2", 495, 240);
    
  }
  if (hc == 3) {
    hc3.cue(0);
    hc3.speed(random(0.5, 2));
    hc3.play();
    
    textSize(40);
    fill(225,225,0);
    text("3", 495 + 70, 240);
  }
}

void playAmb(int ambi) {
  if (ambi == 1) {
    ambience1.cue(0);
    ambience1.speed(random(0.5, 2));
    ambience1.play();
    
    textSize(40);
    fill(225,225,0);
    text("1", 695 - 70, 240);
    
  }
  if (ambi == 2) {
    ambience2.cue(0);
    ambience2.speed(random(0.5, 2));
    ambience2.play();
    
    textSize(40);
    fill(225,225,0);
    text("2", 695, 240);
    
  }
  if (ambi == 3) {
    ambience3.cue(0);
    ambience3.speed(random(0.5, 2));
    ambience3.play();
    
    textSize(40);
    fill(225,225,0);
    text("3", 695 + 70, 240);
  }
}

void playVocal(int vocal) {
  if (vocal == 1) {
    vocal1.cue(0);
    vocal1.speed(random(0.5, 2));
    vocal1.play();
    
    textSize(40);
    fill(225,225,0);
    text("1", 900-70, 240);
    
  }
  if (vocal == 2) {
    vocal2.cue(0);
    vocal2.speed(random(0.5, 2));
    vocal2.play();
    
    textSize(40);
    fill(225,225,0);
    text("2", 900, 240);
    
  }
  if (vocal == 3) {
    vocal3.cue(0);
    vocal3.speed(random(0.5, 2));
    vocal3.play();
    
    textSize(40);
    fill(225,225,0);
    text("3", 900+70, 240);
  }
}

void mouseDragged() {
  ap.mouseDragged();
  av.mouseDragged();
  bp.mouseDragged();
  bv.mouseDragged();
  dp.mouseDragged();
  dv.mouseDragged();  
  kp.mouseDragged();
  kv.mouseDragged();
  rp.mouseDragged();
  rv.mouseDragged();
  sp.mouseDragged();
  sv.mouseDragged();
  d1p.mouseDragged();
  d1v.mouseDragged();
  d2p.mouseDragged();
  d2v.mouseDragged();
  
  f1.mouseDragged();
  dt1.mouseDragged();
  q1.mouseDragged();
  a1.mouseDragged();
  r1.mouseDragged();
  seq.mouseDragged();
  
  vs.mouseDragged();
   
}
 void mousePressed()
{
  ap.mousePressed();
  av.mousePressed();
  bp.mousePressed();
  bv.mousePressed();
  dp.mousePressed();
  dv.mousePressed();  
  kp.mousePressed();
  kv.mousePressed();
  rp.mousePressed();
  rv.mousePressed();
  sp.mousePressed();
  sv.mousePressed();
  d1p.mousePressed();
  d1v.mousePressed();
  d2p.mousePressed();
  d2v.mousePressed();
  
  f1.mousePressed();
  dt1.mousePressed();
  q1.mousePressed();
  a1.mousePressed();
  r1.mousePressed();
  seq.mousePressed();
  
  vs.mousePressed();
  
  int index = (int) Math.floor(mouseX*numBeats/width);   
  int track = (int) Math.floor((mouseY-beatH)*(1/(float)buttonHeight)); //(12/(float)height));
  if (track == 0)
    track1[index] = !track1[index];
  if (track == 1)
    track2[index] = !track2[index];
  if (track == 2)
    track3[index] = !track3[index];
  if (track == 3)
    track4[index] = !track4[index];   
}

void mouseReleased()
{
  for (int i=0;i<notes.length;i++) {

    notes[i]=(int) (Math.floor((seq.get(i)/256)*12)); 
  }
}
