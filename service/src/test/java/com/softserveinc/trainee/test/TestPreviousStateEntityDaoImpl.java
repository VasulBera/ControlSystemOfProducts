package com.softserveinc.trainee.test;


import com.softserveinc.trainee.dao.Impl.PreviousStateEntityDaoImpl;
import com.softserveinc.trainee.entity.administration.PreviousStateEntity;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.persistence.EntityManager;

public class TestPreviousStateEntityDaoImpl {

    @Mock
    private static EntityManager entityManager;

    @InjectMocks
    private static PreviousStateEntityDaoImpl previousStateEntityDao;

    @Before
    public  void doSetup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetEntity(){
        PreviousStateEntity expected = new PreviousStateEntity();
        expected.setId("Cuatomer");
        Mockito.when(entityManager.find(PreviousStateEntity.class, expected.getId())).thenReturn(expected);
        PreviousStateEntity actually = previousStateEntityDao.getPreviousStateEntity(expected.getId());
        Assert.assertEquals(expected, actually);
    }

    @Test
    public void testAddEntityThatExists(){
        PreviousStateEntity previousStateEntity = new PreviousStateEntity();
        previousStateEntity.setId("Cuatomer");
        Mockito.when(entityManager.find(PreviousStateEntity.class, previousStateEntity.getId())).thenReturn(previousStateEntity);
        boolean expected = false;
        boolean actually = previousStateEntityDao.addPreviousStateEntity(previousStateEntity);
        Assert.assertEquals(expected, actually);
    }

    @Test
    public void testAddEntityThatNotExists(){
        PreviousStateEntity previousStateEntity = new PreviousStateEntity();
        previousStateEntity.setId("Cuatomer");
        Mockito.when(entityManager.find(PreviousStateEntity.class, previousStateEntity.getId())).thenReturn(null);
        Mockito.doNothing().when(entityManager).persist(previousStateEntity);
        boolean expected = true;
        boolean actually = previousStateEntityDao.addPreviousStateEntity(previousStateEntity);
        Assert.assertEquals(expected, actually);
    }
    /*@Test
    public void testUpdateEntity(){
        PreviousStateEntity expected = new PreviousStateEntity();
        expected.setId("Cuatomer");
        Mockito.when(entityManager.merge(expected)).thenReturn(expected);
        PreviousStateEntity actually = previousStateEntityDao.updatePreviousStateEntity(expected);
        Assert.assertEquals(expected, actually);

    }*/
}
