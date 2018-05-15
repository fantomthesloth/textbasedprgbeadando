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
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class EnemyTest {
    private Enemy enemy = new Enemy();

    @Before
    public void setUp() {
        enemy.setCurrentHealth(10);
        enemy.setMaxDamage(10);
        enemy.setMinDamage(5);
        enemy.attack();
    }

    @Test
    public void enemyIsAliveIfHealthIsGreaterThanZero() {
        Assert.assertTrue(enemy.getCurrentHealth()>0);
    }

    @Test
    public void attackValueShouldBeBetweenMinDamageAndMaxDamage() {
        Assert.assertTrue(enemy.getMaxDamage() >= enemy.attack());
        Assert.assertTrue(enemy.getMinDamage() <= enemy.attack());
    }

    @Test
    public void enemysHealthShouldBeZeroOrLessIfDamageIsGreaterThanHealth() {
        int damage = 11;
        int healthAfter = enemy.getCurrentHealth() - damage;
        Assert.assertTrue(healthAfter <=0);
    }

    @Test
    public void enemysHealthShouldBeGreaterThanZeroIfDamageIsLessThanHeath(){
        int damage = 9;
        int healthAfter = enemy.getCurrentHealth() - damage;
        Assert.assertTrue(healthAfter > 0);
    }

}
