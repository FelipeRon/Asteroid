// Pin donde se conectan los leds
int pinLed1 = 2;

const int inputPin = 5;
const int inputPin1 = 6;
const int inputPin2 = 7;
const int inputPin3 = 8;

int value = 0;
int value2 = 0;
int value3 = 0;
int value4 = 0;
 
void setup()
{
  // Configuramos como salidas los pines donde se conectan los led
  pinMode(pinLed1, OUTPUT);
  pinMode(inputPin, INPUT);
  
  //  Configurar el puerto serial
  Serial.begin(9600);
}
 
void loop()
{ 
  // Apagar todos los leds siempre que se inicia el ciclo
  digitalWrite(pinLed1, 1); 
  value = digitalRead(inputPin);  //lectura digital de pin
  value2 = digitalRead(inputPin1);  //lectura digital de pin
  value3 = digitalRead(inputPin2);  //lectura digital de pin
  value4 = digitalRead(inputPin3);  //lectura digital de pin
 
  //mandar mensaje a puerto serie en función del valor leido
  Serial.print("a");
  if (value == HIGH) {
      Serial.print("1");
  }
  else {
    Serial.print("0");
  }
  Serial.print("b");
  if (value2 == HIGH) {
      Serial.print("2");
  }
   else {
    Serial.print("0");
  }
  Serial.print("c");
  if (value3 == HIGH) {
      Serial.print("3");
  }
   else {
    Serial.print("0");
  }
  Serial.print("d");
  if (value4 == HIGH) {
      Serial.print("4");
  }
   else {
    Serial.print("0");
  }
  Serial.println("f");
  // Esperar unos milisegundos antes de actualizar
  delay(100);
}
