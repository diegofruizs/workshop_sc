/*
MIT License

Copyright (c) 2017 Universidad de los Andes - ISIS2603

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
 */
package co.edu.uniandes.csw.sportcenter.persistence;

import co.edu.uniandes.csw.sportcenter.entities.SportEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author ISIS2603
 */
@Stateless
public class SportPersistence {

    private static final Logger LOGGER = Logger.getLogger(SportPersistence.class.getName());

    @PersistenceContext(unitName = "SportCenterPU")
    protected EntityManager em;

    public SportEntity create(SportEntity entity) {
        LOGGER.info("Creando un sport ");
         em.persist(entity);
        LOGGER.info("Creando un sport ");
        return entity;
    }

    public SportEntity update(SportEntity entity) {
        LOGGER.log(Level.INFO, "Actualizando sport con id={0}", entity.getId());
        return em.merge(entity);
    }

    public void delete(Long id) {
        LOGGER.log(Level.INFO, "Borrando sport con id={0}", id);
        SportEntity entity = em.find(SportEntity.class, id);
        em.remove(entity);
    }

    public SportEntity find(Long id) {
        LOGGER.log(Level.INFO, "Consultando sport con id={0}", id);
        return em.find(SportEntity.class, id);
    }

    public List<SportEntity> findAll() {
        LOGGER.info("Consultando todas las sportes");
        TypedQuery query = em.createQuery("select u from SportEntity u", SportEntity.class);
        return query.getResultList();
    }

}
