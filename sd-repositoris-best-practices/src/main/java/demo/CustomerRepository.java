/*
 * Copyright 2012-2016 the original author or authors.
 *
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
 */
package demo;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * {@link Repository} to access {@link Customer} instances.
 * 
 * @author Oliver Gierke
 * @author Thomas Darimont
 * @since Step 2
 */
interface CustomerRepository extends PagingAndSortingRepository<Customer, Long>, QueryDslPredicateExecutor<Customer>, QueryByExampleExecutor<Customer> {

	/**
	 * Returns the customer with the given {@link EmailAddress}.
	 * 
	 * @param emailAddress
	 *            the {@link EmailAddress} to search for.
	 * @since Step 2
	 * @return
	 */
	Customer findByEmailAddress(EmailAddress emailAddress);

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.data.repository.CrudRepository#findAll()
	 */
	List<Customer> findAll();

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.data.repository.CrudRepository#save(S)
	 */
	@Transactional(timeout = 10)
	<S extends Customer> S save(S entity);

	/**
	 * Returns the customer with the given {@link EmailAddress} string.
	 * 
	 * @param emailAddress
	 * @return
	 * 
	 * @since Step 8
	 */
	default Customer findByEmailAddress(String emailAddress) {
		return findByEmailAddress(new EmailAddress(emailAddress));
	}
}