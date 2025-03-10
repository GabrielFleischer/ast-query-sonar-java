package checks.spring;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

public class CacheAnnotationsShouldOnlyBeAppliedToConcreteClassesCheckSample {

  @Cacheable("aCache") // Noncompliant
  public interface InterfaceCacheable {
    @Cacheable("aCache") // Noncompliant {{"@Cacheable" annotation should only be applied to concrete classes.}}
  //^^^^^^^^^^^^^^^^^^^^
    String getData(String id);
  }


  @Cacheable("aCache")
  abstract class AbstractClassCacheable {
    @Cacheable("aCache")
    String getData(String id){return "";}
  }

  @Cacheable("aCache")
  class ClassCacheable {
    @Cacheable("aCache")
    String getData(String id){return "";}
  }


  @Cacheable("aCache")
  record RecordCacheable() {
    @Cacheable("aCache")
    String getData(String id){return "";}
  }

  @Cacheable("aCache")
  enum EnumCacheable {
    FOO;
    @Cacheable("aCache")
    String getData(String id){return "";}
  }

  @Cacheable("aCache") // here we apply it on an annotation and not an interface. There is no problem.
  @interface MyAnnotation {}

  @CacheConfig // Noncompliant {{"@CacheConfig" annotation should only be applied to concrete classes.}}
  public interface InterfaceCacheConfig { }

  @CacheConfig
  class ClassCacheConfig { }

  @CachePut("aCache") // Noncompliant {{"@CachePut" annotation should only be applied to concrete classes.}}
  public interface InterfaceCachePut {
    @CachePut("aCache") // Noncompliant
    String getData(String id);
  }

  @CachePut("aCache")
  class ClassCachePut {
    @CachePut("aCache")
    String getData(String id){return "";}
  }

  @CacheEvict("aCache") // Noncompliant {{"@CacheEvict" annotation should only be applied to concrete classes.}}
  public interface InterfaceCacheEvict {
    @CacheEvict("aCache") // Noncompliant
    String getData(String id);
  }

  @CacheEvict("aCache")
  class ClassCacheEvict {
    @CacheEvict("aCache")
    String getData(String id){return "";}
  }

  @Caching // Noncompliant {{"@Caching" annotation should only be applied to concrete classes.}}
  public interface InterfaceCaching {
    @Caching // Noncompliant
    String getData(String id);
  }

  @Caching
  class ClassCaching {
    @Caching
    String getData(String id){return "";}
  }

  interface MyRepository extends Repository<Integer, Integer> {
    @Cacheable("aCache") // compliant, it is the only way to do it in repositories. I guess depending on proxy mode
    // repositories don't work at all
    Integer findById(Integer id);

    @CachePut("aCache") // compliant
    Integer findByIdCachePut(Integer id);

    @CacheEvict("aCache") // compliant
    Integer findByIdCacheEvict(Integer id);

    @org.springframework.cache.annotation.Cacheable("aCache")
    Integer fullyQualifiedCache(Integer i);
  }

  public interface MyCrudRepository extends CrudRepository<Integer, Integer> {
    @CachePut("aCache") // compliant
    Integer findByIdCachePut(Integer id);

    @CacheEvict("aCache") // compliant
    Integer findByIdCacheEvict(Integer id);

    @org.springframework.cache.annotation.Cacheable("aCache")
    Integer fullyQualifiedCache(Integer i);
  }
}
