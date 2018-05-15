/*-
 * #%L
 * Random
 * %%
 * Copyright (C) 2018 University of Debrecen
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
import com.magyart.random.model.Player;
import com.magyart.random.model.PlayerEntity;
import com.magyart.random.model.Town;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.junit.Assert;

public class TownTest {

    PlayerEntity playerEntity = Mockito.mock(PlayerEntity.class);
    Town town = Mockito.mock(Town.class);

    @Before
    public void setUp() {
        playerEntity = new PlayerEntity(new Player());
    }

    @Test
    public void playersGoldIsMoreThanHealUp(){
        Assert.assertTrue(playerEntity.getGold() > town.getHealUp());
    }

    @Test
    public void playersGoldIsMoreThanRefill(){
        Assert.assertTrue(playerEntity.getGold() > town.getRefillPotions());
    }

    @Test
    public void playersGoldIsMoreThanUpgrade(){
        Assert.assertTrue(playerEntity.getGold() > town.getUpgradeWeapon());
    }
}
