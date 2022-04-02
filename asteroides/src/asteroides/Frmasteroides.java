/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroides;

import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.comm.CommPortIdentifier;
import javax.comm.SerialPort;
import javax.comm.UnsupportedCommOperationException;
import javax.imageio.ImageIO;
import javax.swing.Timer;

/**
 *
 * @author nel_g
 */
public class Frmasteroides extends javax.swing.JFrame implements KeyListener{
    
    int derbim; 
    int contador1;
    int a,b,c,d,f;
    int posa,posb,posc,posd,posf;
    private String letra1 ;
    public int dato, nBytes;
    int paleta1;
    int paleta2;
    private CommPortIdentifier idPort;
    private SerialPort puertoSerial;
    public OutputStream salida;
    public InputStream entrada;
    private String nombre;
    private String letra;
    int ubias;
    int direccionav[]={0,0,0,0,0,0,0};
    int i;
    int dificultad;
    int nj = 500;
    int ayudat;
    int choque; 
    int matax = 0; 
    int matay = 0;
    int mast;
    int mastv[]={0,0,0,0,0,0,0};
    int mbala;
    int mbalav[]={0,0,0,0,0,0,0};
    double ax, ay ;
    double distancia;
    double distanciav[]={0,0,0,0,0,0,0};
    double distancianv[]={0,0,0,0,0,0,0};
    int dacc = 1;
    int aleatoriox;
    int aleatorioy;
    int contador = 0;
    int contadora = 0;
    int contadorb = 0;
    int derbim2 = 400;
    int tecla;
    double accX,accY;
    double balaX,balaY;
    double balaxv []={0,0,0,0,0,0,0};
    double balayv []={0,0,0,0,0,0,0};
    double accXa,accYa;
    int mnave;
    double movx = 400;
    double movbx, movby;
    double movbxv []={0,0,0,0,0,0,0};
    double movbyv []={0,0,0,0,0,0,0};
    double movxa ;
    double movxav []={0,0,0,0,0,0,0};
    double movya ;
    double movyav []={0,0,0,0,0,0,0};
    double movy = 300;
    double locationX ;
    double locationY ;
    double locationXa ;
    double locationYa ;
    double locationXb ;
    double locationYb ;
    BufferedImage imgNebula = null;
    BufferedImage imgdebris = null;   
    BufferedImage imgdebris2 = null;
    BufferedImage imgnave = null;
    BufferedImage imgnave2 = null;
    BufferedImage imgasteroid = null;
    BufferedImage imgbala = null;
    BufferedImage imgexplosion = null;
    
    public void keyTyped(KeyEvent e) {}
    public void keyReleased(KeyEvent e) {}
    public void keyPressed(KeyEvent e)
    {
//       System.out.println(e.getKeyCode());
       tecla = e.getKeyCode();
       if (tecla == 39)
       {
           contador +=5;
       }  
       if (tecla == 37)
       {
          contador -= 5;
       }
       if (tecla == 38)
       {
          mnave +=1;
          accX=Math.cos(Math.toRadians(contador%360))*20;
          accY=Math.sin(Math.toRadians(contador%360))*20;  
       }
       if (tecla == 32)
       {
          mbala += 1;
          mbalav[mbala] +=1;
          if (mbala < 6)
          {
              contadorb = contador;
              movbxv[mbala] = movx + 45 ;
              movbyv[mbala] = movy + 45 ;
              balaxv[mbala]=Math.cos(Math.toRadians(contadorb % 360))*40;
              balayv[mbala]=Math.sin(Math.toRadians(contadorb % 360))*40; 
          }
          else
          {
              mbala = 0;
          }
       }

    }
    
    
    public void paint(Graphics g)
    {
        super.paint(g);
 
        g.drawImage(imgNebula, 3, 12, this);
        g.drawImage(imgdebris, derbim, 20, this);
        g.drawImage(imgdebris2, derbim2, 20, this);
        double rotationRequired = Math.toRadians(contador);
        AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
        
        double rotationRequiredb = Math.toRadians(contadorb);
        AffineTransform txb = AffineTransform.getRotateInstance(rotationRequired, locationXb, locationYb);
        AffineTransformOp opb = new AffineTransformOp(txb, AffineTransformOp.TYPE_BILINEAR);
        
        double rotationRequireda = Math.toRadians(contadora);
        AffineTransform txa = AffineTransform.getRotateInstance(rotationRequired, locationXa, locationYa);
        AffineTransformOp opa = new AffineTransformOp(txa, AffineTransformOp.TYPE_BILINEAR);
        
        movx += accX ;
        movy += accY ;
        if (choque == 0)
        {
            for (int x = 0; x < 7; x++)
            {
                movbxv[x] += balaxv[x] ;
                movbyv[x] += balayv[x]; 
            }
        }
        
        Graphics2D g2=(Graphics2D) g;
        g2.drawImage(op.filter(imgnave, null),(int) movx,(int) movy, null);
        
        if (mnave> 0)
        {
            g2.drawImage(op.filter(imgnave2, null),(int) movx ,(int) movy , null);
            mnave = 0;
        }
//        if (mast > 1)
//        {
//            g2.drawImage(opa.filter(imgasteroid, null),(int) movxa,(int) movya, null);
//            if (choque == 0 )
//            {
//                movxa += 5 ;
//                movya += 5 ;
//            }
//            contadora += 5 ;
//        }

        for (int x = 0; x < 7; x++)
        { 
            if (mastv[x] > 1)
            {
                g2.drawImage(opa.filter(imgasteroid, null),(int) movxav[x],(int) movyav[x], null);
                if (direccionav[x] == 1)
                {
                    movxav[x] += (int) (Math.random () * 3 + 5) ;
                    movyav[x] += (int) (Math.random () * 3 + 5);
                }
                else
                {
                    movxav[x] -= (int) (Math.random () * 3 + 5) ;
                    movyav[x] -= (int) (Math.random () * 3 + 5) ;
                }
                contadora += 5 ;
            }
        }
        for (int x = 0; x < 7; x++)
        {
            if (mbalav[x]> 0)
            {
                g2.drawImage(opb.filter(imgbala, null),(int) movbxv[x] ,(int) movbyv[x] , null);
            }
        }
//      nave pase pantalla        
        if (movx > 800)
        {
            movx = movx % 800;
        }
        if (movy > 600 )
        {
            movy = movy % 600;
        }
        if (movx < 0)
        {
            movx = 800;
        }
        if (movy < 0)
        {
            movy = 600;
        }
        for (int y = 0; y < 7; y++)
        {
            ax = Math.pow(((movbxv[mbala] + 5) - (movxav [y] + 45)), 2);
            ay = Math.pow(((movbyv[mbala] + 5) - (movyav [y]+ 45)), 2);
            distanciav[y]=Math.sqrt(ax + ay);;

        }
        
        for (int z = 0; z < 7; z++)
        {
            ax = Math.pow(((movx + 45) - (movxav [z] + 45)), 2);
            ay = Math.pow(((movy + 45) - (movyav [z]+ 45)), 2);
            distancianv[z]=Math.sqrt(ax + ay);;
        }
        for (int x = 0; x < 7; x++)
        {
            if (distancianv[x] < 90)
            {
                mastv[x] = 0;
                choque = 1;
                g.drawImage(imgexplosion,(int) movxav[x] ,(int) movyav [x], (int) movxav[x] + 100 ,(int) movyav[x] + 100, matax, matay, matax + 100, matay + 100, this);
                ubias = x;
            }
        }
        
        for (int x = 0; x < 7; x++)
        {
            if (distanciav[x] < 45)
            {
                mastv[x] = 0;
                mbalav[mbala] = 0;
                choque = 1;
                g.drawImage(imgexplosion,(int) movxav[x] ,(int) movyav [x], (int) movxav[x] + 100 ,(int) movyav[x] + 100, matax, matay, matax + 100, matay + 100, this);
                ubias = x;
            }
        }
//        if (distancia < 45 )
//        {
//            mast = 0;
//            mbala = 0;
//            choque = 1;
//            g.drawImage(imgexplosion,(int) movxa ,(int) movya, (int) movxa + 100 ,(int) movya + 100, matax, matay, matax + 100, matay + 100, this);
//        }
//      asteroide pase pantalla
        for (int y = 0; y < 6; y++)
        {
            if (movxav[y] > 800)
            {
                movxav[y] = movxav[y] % 800;
            }
            if (movyav[y] > 600)
            {
                movyav[y] = movyav[y] % 600;
            }
            if (movxav[y] < 0)
            {
                movxav[y] =  800;
            }
            if (movyav[y] < 0)
            {
                movyav[y] =  600;
            }
            
        }

    }
    Timer temporizador2 = new Timer ( 20,new ActionListener()
    {
        public void actionPerformed ( ActionEvent e )
        {
            
            if (choque == 1)
            {
                matax += 100;
                if (matax == 800)
                {
                   matax = 0;
                   matay += 100;
                } 
                if (matay == 800)
                {
                    choque = 0;
                    matay = 0;
                    mastv[ubias] = 2;
                    direccionav[ubias] = (int) (Math.random () * 2 + 1);
                    movxav[ubias]= (int) (Math.random () * 800 + 1); 
                    movyav[ubias]= (int) (Math.random () * 600 + 1);
                }
            }  
        }
    });    
    Timer temporizador = new Timer ( nj,new ActionListener()
    {
        public void actionPerformed ( ActionEvent e )
        {
            derbim += 10;
            derbim2 += 10;
            ayudat += 1 ;

     
            if (ayudat > 1)
            {
//                mast += 1;
                if (i < 6)
                {
                    i += 1;
                    direccionav[i] = (int) (Math.random () * 2 + 1);
                    mastv[i] += 2;
                    movxav[i]= (int) (Math.random () * 800 + 1); 
                    movyav[i]= (int) (Math.random () * 600 + 1);
                    ayudat=0;
                }
        
            }
            if (derbim2 + 640 > 1040)
            {
                derbim2 = -640;
            }
            if (derbim + 640 > 1040)
            {
                derbim = -640;
            }
            
           
            repaint();
        }
    });
    Timer temporizador3 = new Timer ( 100,new ActionListener()
    {
        public void actionPerformed ( ActionEvent e )
        {
            try {
            salida.write(contador1);
        } catch( IOException exs ) {}
        byte[] bufferLectura = new byte[100];
      
        try {
          while( entrada.available() > 0 ) {
            nBytes = entrada.read( bufferLectura );
            letra = new String(bufferLectura);
            for (int contador = 0; contador < letra.length(); contador++)
            {
                char caracter = letra.charAt(contador);
                String pcadena = Character.toString(caracter);
                if (caracter == 'a')
                {
                    posa = contador;
                }
                if (caracter == 'b')
                {
                    posb = contador;
                }
                if (caracter == 'c')
                {
                    posc = contador;
                }
                if (caracter == 'd')
                {
                    posd = contador;
                }
                if (caracter == 'f')
                {
                    posf = contador;
                }
            } 
          }
          if(posa == 0)
          { 
            a=Integer.parseInt(new String(bufferLectura ).substring(posa+1,posb)); 
            b=Integer.parseInt(new String(bufferLectura ).substring(posb+1,posc)); 
            c=Integer.parseInt(new String(bufferLectura ).substring(posc+1,posd)); 
            d=Integer.parseInt(new String(bufferLectura ).substring(posd+1,posf));
          }
            if ( a == 1)
            {
                contador +=5;
            }
            if( b == 2)
            {
               contador -=5;
            }
            if ( c == 3)
            {
              mnave +=1;
              accX=Math.cos(Math.toRadians(contador%360))*20;
              accY=Math.sin(Math.toRadians(contador%360))*20;  
            }
            if( d == 4)
            {
               mbala += 1;
               mbalav[mbala] +=1;
               if (mbala < 6)
               {
                  contadorb = contador;
                  movbxv[mbala] = movx + 45 ;
                  movbyv[mbala] = movy + 45 ;
                  balaxv[mbala]=Math.cos(Math.toRadians(contadorb % 360))*40;
                  balayv[mbala]=Math.sin(Math.toRadians(contadorb % 360))*40; 
               }
               else
               {
                  mbala = 0;
               }
            }
        }catch( IOException exst ) {}
        System.out.print(new String(bufferLectura ));
        }
        });

    /**
     * Creates new form Frmasteroides
     */
    public Frmasteroides() {
        initComponents();
        this.setLocationRelativeTo(null);
        Frmmenu menu = new Frmmenu();
        dificultad =Integer.parseInt(menu.nivel);
        if (dificultad == 500)
            {
                nj = 500;
            }
            if (dificultad == 350)
            {
                nj = 350;
            }
            if (dificultad == 200)
            {
                nj = 200;
            }
        System.out.print(dificultad);
        addKeyListener(this);
        
        try 
        {
            imgNebula = ImageIO.read(new File("C:\\Users\\nel_g\\Downloads\\Asteroids\\Asteroids\\nebula_blue.png"));
            imgdebris = ImageIO.read(new File("C:\\Users\\nel_g\\Downloads\\Asteroids\\Asteroids\\debris2_blue.png"));
            imgdebris2 = ImageIO.read(new File("C:\\Users\\nel_g\\Downloads\\Asteroids\\Asteroids\\debris2_blue.png"));
            imgnave = ImageIO.read(new File("C:\\Users\\nel_g\\Downloads\\Asteroids\\Asteroids\\nave1.png"));
            locationX = imgnave.getWidth() / 2;
            locationY = imgnave.getHeight() / 2;
            imgnave2 = ImageIO.read(new File("C:\\Users\\nel_g\\Downloads\\Asteroids\\Asteroids\\nave2.png"));
            locationX = imgnave2.getWidth() / 2;
            locationY = imgnave2.getHeight() / 2;
            imgasteroid = ImageIO.read(new File("C:\\Users\\nel_g\\Downloads\\Asteroids\\Asteroids\\asteroid_blue.png"));
            locationXa = imgasteroid.getWidth() / 2;
            locationYa = imgasteroid.getHeight() / 2;
            imgbala = ImageIO.read(new File("C:\\Users\\nel_g\\Downloads\\Asteroids\\Asteroids\\shot1.png"));
            locationXb = imgbala.getWidth() / 2;
            locationYb = imgbala.getHeight() / 2;
            imgexplosion = ImageIO.read(new File("C:\\Users\\nel_g\\Downloads\\Asteroids\\Asteroids\\explosion.hasgraphics.png"));

        } catch (IOException ex) {
            Logger.getLogger(Frmasteroides.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(800, 600));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowActivated

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        AudioClip Sonido;
        Sonido = java.applet.Applet.newAudioClip(getClass().getResource("/asteroides/espacio.wav"));
        Sonido.play();
        temporizador.start();
        temporizador2.start();
        temporizador3.start();
        try{
            nombre= "COM3";//Este es el nombre del puerto del arduino debe ser cambiado seg?n corresponda
            idPort = CommPortIdentifier.getPortIdentifier(nombre);
            puertoSerial=(SerialPort) idPort.open("Comunicacion Serial", 2000);
            entrada = puertoSerial.getInputStream();
            salida=puertoSerial.getOutputStream();
            System.out.println("Puerto " + nombre + " iniciado ...");
     
            try {//los valores que se encuentran a continuaci?n son los par?metros de la comunicaci?n serial, deben ser los mismos en el arduino
                puertoSerial.setSerialPortParams( 9600,
                SerialPort.DATABITS_8,
                SerialPort.STOPBITS_1,
                SerialPort.PARITY_NONE );
            } catch( UnsupportedCommOperationException exy ) {}
    
        } catch (Exception ex) {
            System.out.println("Error en iniciarPuerto() \n"+ex);
        } 
    }//GEN-LAST:event_formWindowOpened

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Frmasteroides.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Frmasteroides.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Frmasteroides.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Frmasteroides.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Frmasteroides().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
