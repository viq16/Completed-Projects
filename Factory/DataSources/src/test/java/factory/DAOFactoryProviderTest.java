package factory;

import factory.enums.provider.DAOFactoryProvider;
import factory.enums.sourceTypes.Source;
import factory.implementation.CSVFactory;
import factory.implementation.DBFFactory;
import factory.implementation.XMLFactory;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class DAOFactoryProviderTest {
    
    @Test
    public void shouldSetSourceOfDataCSV() {
        DAOFactoryProvider.INSTANCE.setSource(Source.CSV);
        assertTrue(DAOFactoryProvider.INSTANCE.getFactoryInstance() instanceof CSVFactory);
    }

    @Test
    public void shouldSetSourceOfDataDBF() {
        DAOFactoryProvider.INSTANCE.setSource(Source.DBF);
        assertTrue(DAOFactoryProvider.INSTANCE.getFactoryInstance() instanceof DBFFactory);
    }

    @Test
    public void shouldSetSourceOfDataXML() {
        DAOFactoryProvider.INSTANCE.setSource(Source.XML);
        assertTrue(DAOFactoryProvider.INSTANCE.getFactoryInstance() instanceof XMLFactory);
    }
}
