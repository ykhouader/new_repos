/*
 * Copyright 2012-2023 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.boot.actuate.autoconfigure.metrics.export.otlp;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.micrometer.registry.otlp.AggregationTemporality;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link OtlpPropertiesConfigAdapter}.
 *
 * @author Eddú Meléndez
 */
class OtlpPropertiesConfigAdapterTests {

	@Test
	void whenPropertiesUrlIsSetAdapterUrlReturnsIt() {
		OtlpProperties properties = new OtlpProperties();
		properties.setUrl("http://another-url:4318/v1/metrics");
		assertThat(new OtlpPropertiesConfigAdapter(properties).url()).isEqualTo("http://another-url:4318/v1/metrics");
	}

	@Test
	void whenPropertiesAggregationTemporalityIsNotSetAdapterAggregationTemporalityReturnsCumulative() {
		OtlpProperties properties = new OtlpProperties();
		assertThat(new OtlpPropertiesConfigAdapter(properties).aggregationTemporality())
			.isSameAs(AggregationTemporality.CUMULATIVE);
	}

	@Test
	void whenPropertiesAggregationTemporalityIsSetAdapterAggregationTemporalityReturnsIt() {
		OtlpProperties properties = new OtlpProperties();
		properties.setAggregationTemporality(AggregationTemporality.DELTA);
		assertThat(new OtlpPropertiesConfigAdapter(properties).aggregationTemporality())
			.isSameAs(AggregationTemporality.DELTA);
	}

	@Test
	void whenPropertiesResourceAttributesIsSetAdapterResourceAttributesReturnsIt() {
		OtlpProperties properties = new OtlpProperties();
		properties.setResourceAttributes(Map.of("service.name", "boot-service"));
		assertThat(new OtlpPropertiesConfigAdapter(properties).resourceAttributes()).containsEntry("service.name",
				"boot-service");
	}

	@Test
	void whenPropertiesHeadersIsSetAdapterHeadersReturnsIt() {
		OtlpProperties properties = new OtlpProperties();
		properties.setHeaders(Map.of("header", "value"));
		assertThat(new OtlpPropertiesConfigAdapter(properties).headers()).containsEntry("header", "value");
	}

	@Test
	void whenPropertiesBaseTimeUnitIsNotSetAdapterBaseTimeUnitReturnsMillis() {
		OtlpProperties properties = new OtlpProperties();
		assertThat(new OtlpPropertiesConfigAdapter(properties).baseTimeUnit()).isSameAs(TimeUnit.MILLISECONDS);
	}

	@Test
	void whenPropertiesBaseTimeUnitIsSetAdapterBaseTimeUnitReturnsIt() {
		OtlpProperties properties = new OtlpProperties();
		properties.setBaseTimeUnit(TimeUnit.SECONDS);
		assertThat(new OtlpPropertiesConfigAdapter(properties).baseTimeUnit()).isSameAs(TimeUnit.SECONDS);
	}

}
