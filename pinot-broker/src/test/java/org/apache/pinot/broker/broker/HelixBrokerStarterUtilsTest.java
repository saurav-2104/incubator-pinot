/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.pinot.broker.broker;

import org.apache.pinot.broker.broker.helix.HelixBrokerStarter;
import org.testng.Assert;
import org.testng.annotations.Test;


public class HelixBrokerStarterUtilsTest {

  @Test
  public void testZkParserUtil1() {
    String zkServers = "hostname1,hostname2";
    String zkAddressForBroker = HelixBrokerStarter.getZkAddressForBroker(zkServers, "helixClusterName");
    String expectedZkAddressForBroker =
        "hostname1/helixClusterName/PROPERTYSTORE,hostname2/helixClusterName/PROPERTYSTORE";
    Assert.assertEquals(zkAddressForBroker, expectedZkAddressForBroker);
  }

  @Test
  public void testZkParserUtil2() {
    String zkServers = "hostname1,hostname2/chroot1/chroot2";
    String zkAddressForBroker = HelixBrokerStarter.getZkAddressForBroker(zkServers, "helixClusterName");
    String expectedZkAddressForBroker =
        "hostname1/chroot1/chroot2/helixClusterName/PROPERTYSTORE,hostname2/chroot1/chroot2/helixClusterName/PROPERTYSTORE";
    Assert.assertEquals(zkAddressForBroker, expectedZkAddressForBroker);
  }

  @Test
  public void testZkParserUtil3() {
    String zkServers = "hostname1:2181,hostname2:2181";
    String zkAddressForBroker = HelixBrokerStarter.getZkAddressForBroker(zkServers, "helixClusterName");
    String expectedZkAddressForBroker =
        "hostname1:2181/helixClusterName/PROPERTYSTORE,hostname2:2181/helixClusterName/PROPERTYSTORE";
    Assert.assertEquals(zkAddressForBroker, expectedZkAddressForBroker);
  }

  @Test
  public void testZkParserUtil4() {
    String zkServers = "hostname1:2181,hostname2:2181/chroot1/chroot2";
    String zkAddressForBroker = HelixBrokerStarter.getZkAddressForBroker(zkServers, "helixClusterName");
    String expectedZkAddressForBroker =
        "hostname1:2181/chroot1/chroot2/helixClusterName/PROPERTYSTORE,hostname2:2181/chroot1/chroot2/helixClusterName/PROPERTYSTORE";
    Assert.assertEquals(zkAddressForBroker, expectedZkAddressForBroker);
  }
}
