import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class UnitTest {


    private PembeliRepository pembeliRepository;

    @Before
    public void setUp() {
        pembeliRepository = new PembeliRepository();
    }

    @Test
    public void testAddPembeliList() {
        Pembeli pembeli = new Pembeli("username", "password");
        pembeliRepository.addPembeliList(pembeli);
        Pembeli[] pembeliList = pembeliRepository.getPembeliList();
        assertNotNull(pembeliList);
        assertEquals(1, pembeliList.length);
        assertEquals(pembeli, pembeliList[0]);
    }

    @Test
    public void testGetPembeliList() {
        Pembeli pembeli = new Pembeli("username", "password");
        pembeliRepository.addPembeliList(pembeli);
        Pembeli[] pembeliList = pembeliRepository.getPembeliList();
        assertNotNull(pembeliList);
        assertEquals(1, pembeliList.length);
        assertEquals(pembeli, pembeliList[0]);
    }

    @Test
    public void testConstructor() {
        Pembeli pembeli = new Pembeli("username", "password");
        assertNotNull(pembeli);
        assertEquals("username", pembeli.getUsername());
        assertEquals("password", pembeli.getPassword());
    }

    @Test
    public void testGetUsername() {
        Pembeli pembeli = new Pembeli("username", "password");
        assertEquals("username", pembeli.getUsername());
    }

    @Test
    public void testGetPassword() {
        Pembeli pembeli = new Pembeli("username", "password");
        assertEquals("password", pembeli.getPassword());
    }
    private PenjualRepository penjualRepository;

    @Before
    public void setUp() {
        penjualRepository = new PenjualRepository();
    }

    @Test
    public void testAddPenjualList() {
        Penjual penjual = new Penjual("username", "password", "namaToko");
        penjualRepository.addPenjualList(penjual);
        Penjual[] penjualList = penjualRepository.getPenjualList();
        assertNotNull(penjualList);
        assertEquals(1, penjualList.length);
        assertEquals(penjual, penjualList[0]);
    }

    @Test
    public void testGetPenjualList() {
        Penjual penjual = new Penjual("username", "password", "namaToko");
        penjualRepository.addPenjualList(penjual);
        Penjual[] penjualList = penjualRepository.getPenjualList();
        assertNotNull(penjualList);
        assertEquals(1, penjualList.length);
        assertEquals(penjual, penjualList[0]);
    }
    @Test
    public void testConstructor() {
        Penjual penjual = new Penjual("username", "password", "namaToko");
        assertNotNull(penjual);
        assertEquals("username", penjual.getUsername());
        assertEquals("password", penjual.getPassword());
        assertEquals("namaToko", penjual.getNamaToko());
    }

    @Test
    public void testGetUsername() {
        Penjual penjual = new Penjual("username", "password", "namaToko");
        assertEquals("username", penjual.getUsername());
    }

    @Test
    public void testGetPassword() {
        Penjual penjual = new Penjual("username", "password", "namaToko");
        assertEquals("password", penjual.getPassword());
    }

    @Test
    public void testGetNamaToko() {
        Penjual penjual = new Penjual("username", "password", "namaToko");
        assertEquals("namaToko", penjual.getNamaToko());
    }

    @Test
    public void testLogin() {
        // Test the login method
    }

    @Test
    public void testRegister() {
        // Test the register method
    }

    @Test
    public void testHariEsok() {
        // Test the hariEsok method
    }
}
