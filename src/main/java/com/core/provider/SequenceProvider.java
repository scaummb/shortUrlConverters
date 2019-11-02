package com.core.provider;

/**
 * @author moubin.mo
 * @date: 2019/9/20 16:59
 */

public interface SequenceProvider {

	long getNextSequence(String var1);

	long getNextSequenceBlock(String var1, long var2);

	void resetSequence(String var1, long var2);

	long getCurrentSequence(String var1);
}
