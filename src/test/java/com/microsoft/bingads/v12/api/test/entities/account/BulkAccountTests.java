package com.microsoft.bingads.v12.api.test.entities.account;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.microsoft.bingads.v12.api.test.entities.account.read.BulkAccountReadTests;

import junit.framework.TestCase;

@RunWith(Suite.class)
@SuiteClasses({BulkAccountReadTests.class})

public class BulkAccountTests extends TestCase {
}
