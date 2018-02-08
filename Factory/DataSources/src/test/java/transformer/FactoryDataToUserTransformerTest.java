package transformer;

import domain.User;
import factory.implementation.CSVFactory;
import factory.implementation.DBFFactory;
import factory.implementation.XMLFactory;
import factory.transformer.FactoryDataToUserTransformer;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class FactoryDataToUserTransformerTest {
    @InjectMocks
    private FactoryDataToUserTransformer dataToUserTransformer = new FactoryDataToUserTransformer();

    @Mock
    private CSVFactory csvFactory;

    @Mock
    private DBFFactory dbfFactory;

    @Mock
    private XMLFactory xmlFactory;

    @Before
    public void setUp() {
        initMocks(this);
        when(csvFactory.getUserById(1)).thenReturn(new User(1, "CSVTestName", 100));
        when(csvFactory.getAllUsers()).thenReturn(Arrays.
                asList(new User(1, "CSVTestName", 100),
                        new User(2, "testName2", 11)));

        when(dbfFactory.getUserById(4)).thenReturn(new User(4, "DBFTestName", 101));
        when(dbfFactory.getAllUsers()).thenReturn(Arrays.
                asList(new User(4, "DBFTestName", 101),
                        new User(5, "testName4", 11)));

        when(xmlFactory.getUserById(7)).thenReturn(new User(7, "XMLTestName", 102));
        when(xmlFactory.getAllUsers()).thenReturn(Arrays.
                asList(new User(7, "XMLTestName", 102),
                        new User(8, "testName6", 11)));
    }

    @Test
    public void shouldTransformCSVDataByIdTest() {
        User user = dataToUserTransformer.trnasformByUserId(csvFactory, 1);
        assertEquals(1, user.getId());
        assertEquals("CSVTestName", user.getName());
        assertEquals(100, user.getAge());
    }

    @Test
    public void shouldTransformDBFDataByIdTest() {
        User user = dataToUserTransformer.trnasformByUserId(dbfFactory, 4);
        assertEquals(4, user.getId());
        assertEquals("DBFTestName", user.getName());
        assertEquals(101, user.getAge());
    }

    @Test
    public void shouldTransformXMLDataByIdTest() {
        User user = dataToUserTransformer.trnasformByUserId(xmlFactory, 7);
        assertEquals(7, user.getId());
        assertEquals("XMLTestName", user.getName());
        assertEquals(102, user.getAge());
    }

    @Test
    public void shouldTransformAllCSVDataTest() {
        List<User> expectedList = new ArrayList();
        expectedList.add(new User(1, "CSVTestName", 100));
        expectedList.add(new User(2, "testName2", 11));
        List<User> actualList = dataToUserTransformer.transformAll(csvFactory);
        assertEquals(expectedList, actualList);
    }

    @Test
    public void shouldTransformAllDBFDataTest() {
        List<User> expectedList = new ArrayList();
        expectedList.add(new User(4, "DBFTestName", 101));
        expectedList.add(new User(5, "testName4", 11));
        List<User> actualList = dataToUserTransformer.transformAll(dbfFactory);
        assertEquals(expectedList, actualList);
    }

    @Test
    public void shouldTransformAllXMLDataTest() {
        List<User> expectedList = new ArrayList();
        expectedList.add(new User(7, "XMLTestName", 102));
        expectedList.add(new User(8, "testName6", 11));
        List<User> actualList = dataToUserTransformer.transformAll(xmlFactory);
        assertEquals(expectedList, actualList);
    }

}
