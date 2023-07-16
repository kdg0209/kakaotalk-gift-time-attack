package com.kakaotalk.gift.domain.member.application.common;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@ContextConfiguration(classes = {TestDatasourceConfig.class})
public abstract class IntegrationTest {
}
