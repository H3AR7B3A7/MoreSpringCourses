import {PortfolioState, PortfolioActions, TransactionsState, PortfolioTransactionsAction} from "../actions/types";
import { Reducer } from "redux";

const portfolioInitialState : PortfolioState = {positions : [], isAuthenticated : false};

export const portfolioReducer : Reducer<PortfolioState, PortfolioActions> = (
    state = portfolioInitialState, action 
    ) => {
        switch(action.type) {
            case "GotPositions" : {
                return {positions: action.positions, isAuthenticated: action.isAuthenticated};
            }
            default:
                return state;
        }
    };

    
const portfolioTransactionsInitialState : TransactionsState = {transactions: []}


export const portfolioTransactionsReducer : Reducer<TransactionsState, PortfolioTransactionsAction> = (
    state = portfolioTransactionsInitialState , action
) => {
    switch(action.type) {
        case 'GotTransactions' : {
            return {transactions: action.transactions}
        }
        default:
            return state;
    }
    
}
