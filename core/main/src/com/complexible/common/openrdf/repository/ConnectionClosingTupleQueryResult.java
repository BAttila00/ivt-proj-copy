/*
 * Copyright (c) 2009-2013 Clark & Parsia, LLC. <http://www.clarkparsia.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.complexible.common.openrdf.repository;

import com.complexible.common.openrdf.query.DelegatingTupleQueryResult;

import org.openrdf.query.QueryEvaluationException;
import org.openrdf.query.TupleQueryResult;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.repository.RepositoryException;

/**
 * <p></p>
 *
 * @author  Michael Grove
 * @since   2.0
 * @version 2.0
 */
public final class ConnectionClosingTupleQueryResult extends DelegatingTupleQueryResult {
	private final RepositoryConnection mConn;

	public ConnectionClosingTupleQueryResult(final RepositoryConnection theConn, final TupleQueryResult theResult) {
		super(theResult);
		mConn = theConn;
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public void close() throws QueryEvaluationException {
		try {
			mConn.close();
		}
		catch (RepositoryException e) {
			throw new QueryEvaluationException(e);
		}
		finally {
			super.close();
		}
	}
}