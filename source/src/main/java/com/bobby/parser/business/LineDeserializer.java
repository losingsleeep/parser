package com.bobby.parser.business;

import com.bobby.parser.LineData;
import com.bobby.parser.exceptions.EmptyLineException;
import com.bobby.parser.exceptions.LineParseException;

/**
 * @author Babak Eghbali (Bob)
 * @since 2018/06/08
 */
public interface LineDeserializer {

    LineData read(String line) throws LineParseException, EmptyLineException;

}
