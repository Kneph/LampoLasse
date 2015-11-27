
/* 

For this project you need:
- Arduino Uno
- GSM/GPRS shield that is compatible with your arduino
- temperature sensor (in this phase we used TMP35)

Send SMS example - Arduino.cc : https://www.arduino.cc/en/Tutorial/GSMExamplesSendSMS
Recieve SMS example - Arduino.cc : https://www.arduino.cc/en/Tutorial/GSMExamplesReceiveSMS
Temperature sensor example - Arduino Playground : http://playground.arduino.cc/ComponentLib/Thermistor2
by Katri Vilonen, Juha Vanhamäki
*/

//GSM gsmAccess;
//GSM_SMS sms;
//TMP36 Pin Variables
int sensorPin = 0; //the analog pin the TMP36's Vout (sense) pin is connected to
                        //the resolution is 10 mV / degree centigrade with a
                        //500 mV offset to allow for negative temperatures
                        
 
/*
 * setup() - this function runs once when you turn your Arduino on
 * We initialize the serial connection with the computer
 */
void setup()
{
  Serial.begin(9600);  //Start the serial connection with the computer
                       //to view the result open the serial monitor
  /*while (!Serial) {
    ; // wait for serial port to connect. Needed for native USB port only
  }

  Serial.println("SMS Messages Sender");

  // connection state
  boolean notConnected = true;

  // Start GSM shield
  // If your SIM has PIN, pass it as a parameter of begin() in quotes
  while (notConnected) {
   if (gsmAccess.begin(PINNUMBER) == GSM_READY) {
     notConnected = false;
    } else {
      Serial.println("Not connected");
      delay(1000);
    }
  }

  Serial.println("GSM initialized");
  */
  
  /*while (!Serial) {
    ; // wait for serial port to connect. Needed for native USB port only
  }

  Serial.println("SMS Messages Receiver");

  // connection state
  boolean notConnected = true;

  // Start GSM connection
  while (notConnected) {
    if (gsmAccess.begin(PINNUMBER) == GSM_READY) {
      notConnected = false;
    } else {
      Serial.println("Not connected");
      delay(1000);
    }
  }

  Serial.println("GSM initialized");
  Serial.println("Waiting for messages");
}
*/
 
}
 
void loop()                     // run over and over again
{
  
 int annettulampotila = 24;
 //getting the voltage reading from the temperature sensor
 int reading = analogRead(sensorPin);  
 
 // converting that reading to voltage, for 3.3v arduino use 3.3
 float voltage = reading * 5.0;
 voltage /= 1024.0; 
 
 // print out the voltage
 //Serial.print(voltage); Serial.println(" volts");
 
 // now print out the temperature
 float temperatureC = (voltage - 0.5) * 100 ;  //converting from 10 mv per degree wit 500 mV offset
                                               //to degrees ((voltage - 500mV) times 100)
 Serial.print(temperatureC); Serial.println(" degrees C");

 delay(1000);   //waiting a second
 
 if (temperatureC <=25 && temperatureC >= 22) {
   Serial.println("JUST SOPIVAA");
   } else if (temperatureC > 25) { 
     Serial.println("HOTTIS");
   } else if (temperatureC < 22){
     Serial.println("KYLYMAA");
   }
   
   if (temperatureC <= annettulampotila) {
   // Lähetä lämmitys signaali
   //Tulosta serialiin juttuja
   Serial.println("lahetetaan signaali");
   }
}
/*
void viestinlahetys(){

  Serial.print("Enter a mobile number: ");
  char remoteNum[20];  // telephone number to send sms
  readSerial(remoteNum);
  Serial.println(remoteNum);

  // sms text
  Serial.print("Now, enter SMS content: ");
  char txtMsg[200];
  readSerial(txtMsg);
  Serial.println("SENDING");
  Serial.println();
  Serial.println("Message:");
  Serial.println(txtMsg);

  // send the message
  sms.beginSMS(remoteNum);
  sms.print(txtMsg);
  sms.endSMS();
  Serial.println("\nCOMPLETE!\n");
}


//  Read input serial

int readSerial(char result[]) {
  int i = 0;
  while (1) {
    while (Serial.available() > 0) {
      char inChar = Serial.read();
      if (inChar == '\n') {
        result[i] = '\0';
        Serial.flush();
        return 0;
      }
      if (inChar != '\r') {
        result[i] = inChar;
        i++;
      }
    }
  }
}
*/

/*
void vestinvastaanotto() {
  char c;

  // If there are any SMSs available()
  if (sms.available()) {
    Serial.println("Message received from:");

    // Get remote number
    sms.remoteNumber(senderNumber, 20);
    Serial.println(senderNumber);

    // An example of message disposal
    // Any messages starting with # should be discarded
    if (sms.peek() == '#') {
      Serial.println("Discarded SMS");
      sms.flush();
    }

    // Read message bytes and print them
    while (c = sms.read()) {
      Serial.print(c);
    }

    Serial.println("\nEND OF MESSAGE");

    // Delete message from modem memory
    sms.flush();
    Serial.println("MESSAGE DELETED");
  }

  delay(1000);

}
*/

