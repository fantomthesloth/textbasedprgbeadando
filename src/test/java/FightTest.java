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
import com.magyart.random.model.Enemy;
import com.magyart.random.model.Player;
import com.magyart.random.model.PlayerEntity;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class FightTest {

    PlayerEntity playerEntity = Mockito.mock(PlayerEntity.class);
    Enemy enemy = Mockito.mock(Enemy.class);

    @Before
    public void setUp() {
        playerEntity = new PlayerEntity(new Player());
    }

    @Test
    public void fight(){
        //TODO
    }

    @Test
    public void playerIsAliveIfHealthIsGreaterThanZero(){
        Assert.assertTrue(playerEntity.getCurrentHealth() > 0);
    }

    @Test
    public void playerHasMoreThanZeroPotions() {
        Assert.assertTrue(playerEntity.getNumberOfPotions() > 0);

    }

    @Test
    public void playerHasMaxHealth(){
        Assert.assertEquals(playerEntity.getCurrentHealth(), playerEntity.getMaxHealth());
    }

    @Test
    public void healthDiffereneIsLessThan40Percent(){
        int fortyPercent = (int) (playerEntity.getMaxHealth() * 0.4);
        Assert.assertTrue(playerEntity.getMaxHealth()-playerEntity.getCurrentHealth() <= fortyPercent);
    }

    @Test
    public void playerHealsToMaxHpIfHasLessThan40PercentHealthDifference(){
        if(playerEntity.getMaxHealth()-playerEntity.getCurrentHealth() <= playerEntity.getMaxHealth() * 0.4){
            playerEntity.setMaxHealth(playerEntity.getMaxHealth());
            Assert.assertEquals(playerEntity.getCurrentHealth(), playerEntity.getMaxHealth());
        }
    }

    @Test
    public void playerHeals40Percent(){
        int fortyPercent = (int) (playerEntity.getMaxHealth() * 0.4);
        if(playerEntity.getMaxHealth()-playerEntity.getCurrentHealth() > playerEntity.getMaxHealth() * 0.4){
            Assert.assertTrue(playerEntity.getCurrentHealth()+ fortyPercent < playerEntity.getMaxHealth());
        }
    }
}
