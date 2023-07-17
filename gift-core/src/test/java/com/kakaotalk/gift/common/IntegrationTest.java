package com.kakaotalk.gift.common;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@ContextConfiguration(classes = {TestDatasourceConfig.class})
public abstract class IntegrationTest {
}
