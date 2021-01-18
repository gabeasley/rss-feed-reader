package parser;

import TestData.TestData;
import com.gab.reader.model.dictionary.Dictionary;
import com.gab.reader.parser.DictionaryParser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;

import static com.gab.reader.constant.Constant.TEST_RESOURCE_PATH;

public class DictionaryParserTest {
    @InjectMocks
    private DictionaryParser dictionaryParser;

    @Before
    public void setup() {
        dictionaryParser = new DictionaryParser();
    }

    @Test
    public void createDictionaryTest() {
        Dictionary actual = dictionaryParser.createDictionary(TEST_RESOURCE_PATH);
        Dictionary expected = TestData.testDictionary();
        Assert.assertEquals(expected,actual);
    }
}
