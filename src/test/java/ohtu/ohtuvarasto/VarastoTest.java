package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    Varasto varasto1;
    Varasto varasto2;
    Varasto varasto3;
    Varasto varasto4;
    Varasto varasto5;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
        varasto1 = new Varasto(-1);
        varasto2 = new Varasto(10, 5);
        varasto3 = new Varasto(-10, 5);
        varasto4 = new Varasto(10, 15);
        varasto5 = new Varasto(10, -5);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void konstruktoriLuoEpätyhjänVaraston() {
        assertEquals(5, varasto2.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus2() {
        assertEquals(10, varasto2.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void uudellaNegatiivisellaVarastollaOikeaTilavuus() {
        assertEquals(0, varasto1.getTilavuus(), vertailuTarkkuus);
    }
    @Test
    public void uudellaNegatiivisellaVarastollaOikeaTilavuus2() {
        assertEquals(0, varasto3.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void uudellaVarastollaOikeaSaldo() {
        assertEquals(5, varasto2.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void uudellaVarastollaNegatiivinenSaldoNolla() {
        assertEquals(0, varasto5.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void uudellaVarastollaLiianSuuriSaldo() {
        assertEquals(10, varasto4.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void ylittäminenTäyttääVaraston() {
        varasto.lisaaVarastoon(12);

        // varaston saldo tulisi olla yhtä suuri kuin sen tilavuus eli 10
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }    

    @Test
    public void liikaaOttaminenTyhjentää() {
        varasto.lisaaVarastoon(3);
        varasto.otaVarastosta(4);

        // varaston saldo tulisi olla 0
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }  

    @Test
    public void negatiivinenLisäys() {
        varasto.lisaaVarastoon(8);

        varasto.lisaaVarastoon(-2);

        // saldon tulisi olla 0 + 8 - 0 = 8
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void negatiivinenOtto() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(-2);

        // saldon tulisi olla 0 + 8 - 0 = 8
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void toStringKoe() {

        assertEquals("saldo = 0.0, vielä tilaa 10.0", varasto.toString());
    } 
}