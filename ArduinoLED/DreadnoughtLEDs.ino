#include <FastLED.h>

// How many leds in your strip?
#define NUM_LEDS 64

#define DATA_PIN 7

//Pin 1 and pin 2
int shooterLEDMode = 1;
int foundTarget = 2;

// Define the array of leds
CRGB leds[NUM_LEDS];

void setup() {
  LEDS.addLeds<WS2812B, DATA_PIN, GRB>(leds, NUM_LEDS);
  LEDS.setBrightness(50);

  pinMode(shooterLEDMode, INPUT)
  pinMode(foundTarget, INPUT)
}

void fadeall() {
  for (int i = 0; i < NUM_LEDS; i++) {
    leds[i].nscale8(250);
  }
}

void loop() {

  int LEDMode = digitalRead(shooterLEDMode)
  int targetFound = digitalRead(foundTarget)



  if (LEDMode == 1) {
    //Write some code that makes larson scanner faster
  } else {
    if (targetFound == 1) {
      //Give indication for that we see target
    } else {
      //write slower larson
    }
  }




  // First slide the led in one direction
  for (int i = 0; i < NUM_LEDS; i++) {
    // Set the i'th led to red
    leds[i] = CRGB::Red;
    // Show the leds
    FastLED.show();
    fadeall();
    delay(10);
  }
  for (int i = 0; i < NUM_LEDS; i++) {
    // Set the i'th led to red
    leds[i] = CRGB::Blue;
    // Show the leds
    FastLED.show();
    fadeall();
    delay(10);
  }
  for (int i = 0; i < NUM_LEDS; i++) {
    // Set the i'th led to red
    leds[i] = CRGB::Purple;
    // Show the leds
    FastLED.show();
    fadeall();
    delay(10);
  }

}
